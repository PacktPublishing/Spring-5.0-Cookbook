package org.packt.secured.mvc.core.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.packt.secured.mvc.core.user.AppUserDetails;
import org.packt.secured.mvc.dao.LoginDao;
import org.packt.secured.mvc.model.data.AccountLogin;
import org.packt.secured.mvc.model.data.RolePermission;
import org.packt.secured.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("authJdbcUserService")
public class AuthJdbcUserService implements UserDetailsService{
	
	@Autowired
	private LoginDao loginDaoImpl;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountLogin login = loginDaoImpl.getUser(username);
		System.out.println("sherwin");
		
		if(login == null){
			throw new UsernameNotFoundException("Invalid User");
		}
		
		UserDetails user = new AppUserDetails(login.getUsername(),login.getEncPassword(),true, true, true, true, getAuthorities(username, login));
		
		
	    return user;
	}
	
	private Set<GrantedAuthority> getAuthorities(String username, AccountLogin login){
		
		List<RolePermission> roleperms = loginDaoImpl.getUserGrantedAuthority(login.getId());
		
		Set<String> roles = new HashSet<>();
		for(RolePermission rp: roleperms){
			roles.add(loginDaoImpl.getUserRole(rp.getRoleId()).getName());
		}
		
		Set<String> perms = new HashSet<>();
		for(RolePermission rp: roleperms){
			perms.add(loginDaoImpl.getPermission(rp.getPermissionId()).getName());
		}
		System.out.println(perms);
		roles.addAll(perms);
		System.out.println(roles);
		
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(String role : roles) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            authorities.add(grantedAuthority);
        }
      
        return authorities;
    }

}
