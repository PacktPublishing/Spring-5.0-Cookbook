package org.packt.aop.transaction.core;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@Aspect
public class InterceptReqMenu {
	
	private Logger logger = Logger.getLogger(InterceptReqMenu.class);

	@Pointcut("within(@org.springframework.stereotype.Controller *)") //we define a pointcut for all controllers
	public void controllerPointcut() {}
	 
	@Pointcut("execution(* org.packt.aop.transaction.controller.LoginController.menu(..))") // the methods that ends in getViewNew are join to this pointcut
	public void menuPointcut() {}
	
	@Around("controllerPointcut() && menuPointcut() && args(model, req)")
	public String validateCredentials(ProceedingJoinPoint joinPoint,  Model model , HttpServletRequest req) throws Throwable  {
		//HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		logger.info("executing " + joinPoint.getSignature().getName());
		boolean authenticated = (Boolean) req.getSession().getAttribute("authenticated");
		boolean empty_login = (Boolean) req.getSession().getAttribute("empty");
		if(empty_login){
			return "empty_login";
		}
		
		if(!authenticated){
			logger.info("not authenticated redirecting to the login page");
 	        return "login";
	    }
	   logger.info("authenticated redirecting to the menu page");   
	   joinPoint.proceed();
	   return "menu";
	}

}
