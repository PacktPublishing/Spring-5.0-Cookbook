package org.packt.secured.mvc.core.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

public class AppAnonAuthFilter extends AnonymousAuthenticationFilter {
	private String principal;
	private String key;
	private List<GrantedAuthority> authorities;
	
    public AppAnonAuthFilter(String key) {
		super(key);
		this.key = key;
	}
	
	 public AppAnonAuthFilter(String key, Object principal, List<GrantedAuthority> authorities) {
		 super(key, principal, authorities);
		 this.key = key;
		 this.principal = principal.toString();
		 this.authorities = authorities;
     }
	
	
	@Override
	protected Authentication createAuthentication(HttpServletRequest request) {
		System.out.println("ANON FILTER");
		if(principal.equalsIgnoreCase(request.getParameter("username")) ){
			 AnonymousAuthenticationToken authTok = new AnonymousAuthenticationToken(key, principal, authorities);
			 SecurityContext context = SecurityContextHolder.getContext();
			 context.setAuthentication(authTok);
		 return authTok;
		}
	    return null;
	}
}
