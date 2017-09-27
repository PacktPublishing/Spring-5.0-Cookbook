package org.packt.secured.mvc.core.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("failure");
		String targetUrl = "";
		if(exception instanceof BadCredentialsException){
			targetUrl = "/login.html?error=" + exception.getMessage();
		}
		else {
			targetUrl = "/login.html?error=" + true;
		}
		  
		if (response.isCommitted()) {
	            System.out.println("Internal problem in redirection");
	            return;
	    }
    
	    redirectStrategy.sendRedirect(request, response, targetUrl);
	}
}
