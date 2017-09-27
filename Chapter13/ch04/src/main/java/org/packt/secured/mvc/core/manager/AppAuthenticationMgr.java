package org.packt.secured.mvc.core.manager;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AppAuthenticationMgr implements AuthenticationManager {
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		System.out.println(AppAuthenticationMgr.class);
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		if (name.equalsIgnoreCase("sjctrags") && password.equalsIgnoreCase("sjctrags")) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new UsernamePasswordAuthenticationToken(name, password, authorities);

		} else if (name.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new UsernamePasswordAuthenticationToken(name, password, authorities);
		} else if (name.equalsIgnoreCase("hradmin") && password.equalsIgnoreCase("hradmin")) {
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_HR"));
			return new UsernamePasswordAuthenticationToken(name, password, authorities);
		} else if(name.equalsIgnoreCase("guest")){
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
			return new AnonymousAuthenticationToken(name, "ANONYMOUS", authorities);
		} else{
			throw new BadCredentialsException("Not Valid User");	
		}
	}
}
