package org.packt.aop.transaction.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class InterceptReqControllerAspect {
	private Logger logger = Logger.getLogger(InterceptReqControllerAspect.class);
	
	@Pointcut("within(@org.springframework.stereotype.Controller *)") //we define a pointcut for all controllers
	public void classPointcut() {}
	 
	@Pointcut("execution(* *intercept(..))") // the methods that ends in getViewNew are join to this pointcut
	public void methodPointcut() {}
	 
	/**
	* Operations*/
	 
	@Around("classPointcut() && methodPointcut()")
	public String logRequest(ProceedingJoinPoint joinPointp) throws Throwable {
		
	 
		System.out.println("Starting request...");
		logger.info("Starting request...");
		joinPointp.proceed();
	    System.out.println("Stopping request");
	    logger.info("Stopping request...");
	    return "Hello Ortigas";
	}

}
