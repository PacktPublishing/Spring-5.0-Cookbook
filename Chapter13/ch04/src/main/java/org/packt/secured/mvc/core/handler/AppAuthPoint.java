package org.packt.secured.mvc.core.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class AppAuthPoint extends LoginUrlAuthenticationEntryPoint {
	
	@Autowired
	private AuthenticationTrustResolver trustResolver;

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public AppAuthPoint(final String loginFormUrl) {
        super(loginFormUrl);
    }

    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

      	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  if (auth == null || trustResolver.isAnonymous(auth)) {
			   redirectStrategy.sendRedirect(request, response, "/deptanon.html");
		   }
		   else{
			   String redirectUrl = buildRedirectUrlToLoginPage(request, response, authException);
			   redirectStrategy.sendRedirect(request, response, redirectUrl);
		   }
     }
}
