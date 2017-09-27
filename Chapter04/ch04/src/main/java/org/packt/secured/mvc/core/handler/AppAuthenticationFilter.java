package org.packt.secured.mvc.core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AppAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		System.out.println("AUTH FILTER");
		
		
		Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
		System.out.println(roles);
		
		String name = obtainPassword(request);
        String password = obtainUsername(request);
		
        
		UsernamePasswordAuthenticationToken userDetails = new UsernamePasswordAuthenticationToken(name, password, authorities);
		setDetails(request, userDetails);	
		chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		response.sendRedirect("/ch04/login.html?error=true");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("AUTH FILTER ATTEMPT");
		String name = obtainPassword(request);
        String password = obtainUsername(request);
        
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = null;
        if(context.getAuthentication() == null){
        	auth = new UsernamePasswordAuthenticationToken(name, password);
        	setDetails(request, (UsernamePasswordAuthenticationToken) auth);	
        }else{
        	auth = (AnonymousAuthenticationToken) context.getAuthentication();
        	return auth;
        }       	
       return auth;
	}
}
