package org.packt.aop.transaction.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.packt.aop.transaction.model.data.RolePermission;
import org.packt.aop.transaction.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class DeleteAuthorizeAspect {
	
private Logger logger = Logger.getLogger(DeleteAuthorizeAspect.class);

    @Autowired
    private LoginService loginServiceImpl;
	
	@Pointcut("within(@org.springframework.stereotype.Controller *))") 
	public void classPointcut() {}
	 
	@Pointcut("execution(* org.packt.aop.transaction.controller.EmployeeController.deleteRecord(..))") 
	public void delPointcut() {}
	
	@Around("classPointcut() && delPointcut()  && @annotation(mapping)")
	public String delEmployee(ProceedingJoinPoint joinPoint,  RequestMapping mapping) throws Throwable{
		   HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		   logger.info("executing " + joinPoint.getSignature().getName());
		   int userId = (Integer)req.getSession().getAttribute("userId");
		   System.out.println("userId" + userId);
		   
		   List<RolePermission> permission = loginServiceImpl.getPermissionSets(userId);
		   if(isAuthroize(permission)){
			   logger.info("user " + userId + " is authroied to delete");
			   joinPoint.proceed();
			   return "menu";
		   }else{
			   logger.info("user " + userId + " is NOT authroied to delete");
			   return "banned";
		   }
	}
	
	
	private boolean isAuthroize( List<RolePermission> permission){
		
		Set<String> userRoles = new HashSet<>();
		Set<String> userPerms = new HashSet<>();
		
		for(RolePermission rp: permission){
			userRoles.add(loginServiceImpl.getUserRole(rp.getRoleId()).getName());
			userPerms.add(loginServiceImpl.getPermission(rp.getPermissionId()).getName());
		}
		
		if(userRoles.contains("ROLE_HR")){
		   return true;	
		}
		return false;
		
	}
}
