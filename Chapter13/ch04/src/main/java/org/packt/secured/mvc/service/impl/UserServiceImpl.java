package org.packt.secured.mvc.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.packt.secured.mvc.core.password.AppPasswordEncoder;
import org.packt.secured.mvc.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Override
	public String getUserCredentials(String username) {
		Map<String, String> credentials = new HashMap<>();
		AppPasswordEncoder encoder = new AppPasswordEncoder();
		
		// Without salt	
		/*
		credentials.put("sjctrags",encoder.md5Encoder("sjctrags", null));
		credentials.put("admin", encoder.md5Encoder("admin", null));
		credentials.put("hradmin", encoder.md5Encoder("hradmin", null));
		*/
		
		// With Salt (username as salt)
		credentials.put("sjctrags",encoder.md5Encoder("sjctrags", "sjctrags"));
		credentials.put("admin", encoder.md5Encoder("admin", "admin"));
		credentials.put("hradmin", encoder.md5Encoder("hradmin", "hradmin"));
		
		
		return credentials.get(username);
	}

	@Override
	public Set<String> getuserRoles(String username) {
		Map<String, Set<String>> roles = new HashMap<>();
		Set<String> userA = new HashSet<>();
		Set<String> userB = new HashSet<>();
		Set<String> userC = new HashSet<>();
		userA.add("ROLE_USER");
		userB.add("ROLE_ADMIN");
		userB.add("ROLE_USER");
		userC.add("ROLE_HR");
		userC.add("ROLE_ADMIN");
		userC.add("ROLE_USER");
		roles.put("sjctrags", userA);
		roles.put("admin", userB);
		roles.put("hradmin", userC);
		return roles.get(username);
	}

}
