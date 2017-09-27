package org.packt.secured.mvc.core.manager;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AppHRProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println(AppHRProvider.class);
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (name.equalsIgnoreCase("hradmin") && password.equalsIgnoreCase("hradmin")) {
			 Set<SimpleGrantedAuthority> authorities = new HashSet<>();
             authorities.add(new SimpleGrantedAuthority("ROLE_HR"));
             return new UsernamePasswordAuthenticationToken(name, password,authorities);
		} else {
			throw new BadCredentialsException("Invalid HR User");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
