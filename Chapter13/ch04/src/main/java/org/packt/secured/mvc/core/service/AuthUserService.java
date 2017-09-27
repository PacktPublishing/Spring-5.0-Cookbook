package org.packt.secured.mvc.core.service;

import java.util.HashSet;
import java.util.Set;

import org.packt.secured.mvc.core.password.AppPasswordEncoder;
import org.packt.secured.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("authUserService")
public class AuthUserService implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String password = userService.getUserCredentials(username);
		
		if(password == null){
			throw new UsernameNotFoundException("Invalid User");
		}
		
		UserDetails user = new User(username, password, true, true, true, true, getAuthorities(username));
	    return user;
	}
	
	private Set<GrantedAuthority> getAuthorities(String username){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(String role : userService.getuserRoles(username)) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            authorities.add(grantedAuthority);
        }
      
        return authorities;
    }
}
