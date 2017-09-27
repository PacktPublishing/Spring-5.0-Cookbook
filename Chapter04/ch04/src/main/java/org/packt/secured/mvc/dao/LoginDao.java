package org.packt.secured.mvc.dao;

import java.util.List;

import org.packt.secured.mvc.model.data.AccountLogin;
import org.packt.secured.mvc.model.data.Permission;
import org.packt.secured.mvc.model.data.Role;
import org.packt.secured.mvc.model.data.RolePermission;

public interface LoginDao {
	
	
	public List<Role> getUserRoles();
	public Role getUserRole(int id);
	
	public List<Permission> getPermissions();
	public Permission getPermission(int id);
	
	public List<RolePermission> getUserGrantedAuthority(int userId);
	
	
	public List<AccountLogin> getUsers();
	public AccountLogin getUser(String username);

}
