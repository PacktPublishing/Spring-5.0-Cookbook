package org.packt.aop.transaction.core;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Component
@Aspect
public class LoginProxyAspect {
	
	private Logger logger = Logger.getLogger(LoginProxyAspect.class);
	
	@Pointcut("within(@org.springframework.stereotype.Controller *))") 
	public void classPointcut() {}
	 
	@Pointcut("execution(* org.packt.aop.transaction.controller.LoginController.login(..))") 
	public void loginPointcut() {}
	
	@Before("classPointcut() && loginPointcut() && args(model,req) && @annotation(mapping)")
	public String browserCheck(JoinPoint joinPoint, Model model, HttpServletRequest req, RequestMapping mapping) throws ServletException, IOException{
		
	   // IP Address checking -- TO DO LIST
       // String ipAddress = req.getRemoteAddr();
	   logger.info("executing " + joinPoint.getSignature().getName());
	   logger.warn("MVC application trying to check browser type...");
       String loginRequestMethod = mapping.method()[0].name();
       String username = req.getParameter("username");
	   String password = req.getParameter("password");
	 
	   req.setAttribute("username", username);
	   req.setAttribute("password", password);
	   
	   logger.info("executing " + joinPoint.getSignature().getName() + " which is a " + loginRequestMethod + " request");
       if(loginRequestMethod.equalsIgnoreCase("GET")){
    	    Enumeration<String> headers = req.getHeaderNames();
            while(headers.hasMoreElements()){
            	String headerName = headers.nextElement(); 	
            	if(headerName.equalsIgnoreCase("user-agent")){
            	     String browserType = req.getHeader(headerName);
            	     if(browserType.contains("Chrome")){
            	    	req.setAttribute("browserNo", 1);   
            	    	logger.info("MVC application uses Chrome...");
            	     }else if (browserType.contains("Firefox")){
            	    	 req.setAttribute("browserNo", 2);
            	    	 logger.info("MVC application uses Firefox...");
            	     }else{
            	    	 req.setAttribute("browserNo", 3);
            	    	 logger.info("MVC application stops because browser not supported...");
            	     }
            	   break;
            	}
            }
       }
		return "login";
	}
	
	
}
