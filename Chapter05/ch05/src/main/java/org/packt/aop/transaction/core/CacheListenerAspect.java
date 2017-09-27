package org.packt.aop.transaction.core;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Component
@Aspect
public class CacheListenerAspect {
	
	@Autowired
	private CacheManager cacheManager;
	
	private Logger logger = Logger.getLogger(CacheListenerAspect.class);
		
	@Around("execution(* org.packt.aop.transaction.dao.impl.EmployeeDaoImpl.getEmployees(..))")
	public Object cacheMonitor(ProceedingJoinPoint joinPoint) throws Throwable  {
	   logger.info("executing " + joinPoint.getSignature().getName());
	   Cache cache = cacheManager.getCache("employeesCache");
	  
	   logger.info("cache detected is  " + cache.getName());
	   logger.info("begin caching.....");
	   String key = joinPoint.getSignature().getName();
	   logger.info(key);
	   if(cache.get(key) == null){
		   logger.info("caching new Object.....");
		   Object result = joinPoint.proceed();
		   cache.put(new Element(key, result)); 	   
		   return result;
	   }else{
		   logger.info("getting cached Object.....");
		   return cache.get(key).getObjectValue();
	   }
	}
}
