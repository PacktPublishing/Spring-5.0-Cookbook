package org.packt.aop.transaction.dao;

import java.util.List;

import org.packt.aop.transaction.model.data.AccountLogin;
import org.packt.aop.transaction.model.data.Permission;
import org.packt.aop.transaction.model.data.Role;
import org.packt.aop.transaction.model.data.RolePermission;


public interface LoginDao {
	
	public List<Role> getUserRoles();
	public Role getUserRole(int id);
	
	public List<Permission> getPermissions();
	public Permission getPermission(int id);
	
	public List<RolePermission> getUserGrantedAuthority(int userId);
	
	public List<AccountLogin> getUsers();
	public AccountLogin getUser(String username);

}
