package org.packt.aop.transaction.service.impl;

import java.util.List;

import org.packt.aop.transaction.dao.LoginDao;
import org.packt.aop.transaction.model.data.AccountLogin;
import org.packt.aop.transaction.model.data.Permission;
import org.packt.aop.transaction.model.data.Role;
import org.packt.aop.transaction.model.data.RolePermission;
import org.packt.aop.transaction.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDao loginDaoImpl;

	@Override
	public List<AccountLogin> getUserAccounts() {
		return loginDaoImpl.getUsers();
	}

	@Override
	public AccountLogin getUserAccount(String username) {
		return loginDaoImpl.getUser(username);
	}

	@Override
	public Role getUserRole(int id) {
		return loginDaoImpl.getUserRole(id);
	}

	@Override
	public Permission getPermission(int id) {
		return loginDaoImpl.getPermission(id);
	}

	@Override
	public List<RolePermission> getPermissionSets(int userId) {
		return loginDaoImpl.getUserGrantedAuthority(userId);
	}

}
