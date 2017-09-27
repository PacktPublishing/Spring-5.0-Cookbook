package org.packt.aop.transaction.core;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.packt.aop.transaction.model.data.AccountLogin;
import org.packt.aop.transaction.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

@Component
@Aspect
public class LoginAuthAspect {
	
	private Logger logger = Logger.getLogger(LoginAuthAspect.class);
	
	@Autowired
	private LoginService loginServiceImpl;
	
	@Autowired
	private Map<String,Integer> authStore;
	
	@Pointcut("within(@org.springframework.stereotype.Controller *)") 
	public void classPointcut() {}
	 
	@Pointcut("execution(* org.packt.aop.transaction.controller.LoginController.loginSubmit(..))") 
	public void loginSubmitPointcut() {}
	
	
	@Before("classPointcut() && loginSubmitPointcut()  && @annotation(mapping)")
	public void registerParams(JoinPoint joinPoint,  RequestMapping mapping) throws ServletException, IOException{
		   HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		   logger.info("executing " + joinPoint.getSignature().getName());
		   String loginRequestMethod = mapping.method()[0].name();
		   logger.info("executing " + joinPoint.getSignature().getName() + " which is a " + loginRequestMethod + " request");
	       if(loginRequestMethod.equalsIgnoreCase("POST")){
			   String username =  req.getParameter("username");
			   String password =  req.getParameter("password");
			  logger.warn("MVC application detected access from user: " + username + " with password: " + password );
		   }
	}
	
		
	@After("classPointcut() && loginSubmitPointcut()  && @annotation(mapping)")
	public void authCredentials(JoinPoint joinPoint, RequestMapping mapping) throws ServletException, IOException{
		   HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		  // ServletWebRequest servletWebRequest=new ServletWebRequest(req);
		  // HttpServletResponse response=servletWebRequest.getResponse();
		   logger.info("executing " + joinPoint.getSignature().getName());
		   String loginRequestMethod = mapping.method()[0].name();
		   logger.info("executing " + joinPoint.getSignature().getName() + " which is a " + loginRequestMethod + " request");
	       if(loginRequestMethod.equalsIgnoreCase("POST")){
	    	   String username =  (String)req.getParameter("username");
	    	   String password = (String) req.getParameter("password");
	    	       	   
	    		   try{
	    			   AccountLogin access = loginServiceImpl.getUserAccount(username.trim());
		    		   req.getSession().setAttribute("empty", false);
			   	       req.getSession().setAttribute("authenticated", false); 
			   	       if(access != null){
			   	    	if(access.getPassword().equalsIgnoreCase(password.trim())){
			   	    		logger.info("user " + username +" with password " + password + " valid");
			   	    		if(authStore.containsKey(username)){
			   	    			if(authStore.get(username) == 2){
			   	    				logger.info("user " + username +" with password " + password + " is already logged in");
				   	    			req.getSession().setAttribute("authenticated", false);
			   	    			}else{
			   	    				int numSess = authStore.get(username);
			   	    				authStore.put(username, ++numSess);
			   	    				req.getSession().setAttribute("authenticated", true);
						   	    	req.getSession().setAttribute("userId", access.getId());
			   	    			}
			   	    		}else{
			   	    			authStore.put(username, 1);
			   	    			req.getSession().setAttribute("authenticated", true);
					   	    	req.getSession().setAttribute("userId", access.getId());
			   	    		}
				   	    	
				   	     }
			   	       }
		    	   
	    		   }catch(Exception e){
	    			   req.getSession().setAttribute("empty", true);
	    		   }
	    		   
	    	  
	   	      
	       }
	   }

}
