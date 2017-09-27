package org.packt.aop.transaction.service;

import java.util.List;
import java.util.Set;

import org.packt.aop.transaction.model.data.AccountLogin;
import org.packt.aop.transaction.model.data.Permission;
import org.packt.aop.transaction.model.data.Role;
import org.packt.aop.transaction.model.data.RolePermission;

public interface LoginService {
	
	public List<AccountLogin> getUserAccounts();
	public AccountLogin getUserAccount(String username);
	
    public Role getUserRole(int id);
	public Permission getPermission(int id);
	
	public List<RolePermission> getPermissionSets(int userId);
	
}
