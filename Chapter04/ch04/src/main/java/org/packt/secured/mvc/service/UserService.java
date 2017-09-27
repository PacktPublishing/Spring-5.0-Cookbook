package org.packt.secured.mvc.service;

import java.util.Set;

public interface UserService {
	
	public String getUserCredentials(String username);
	public Set<String> getuserRoles(String username);

}
