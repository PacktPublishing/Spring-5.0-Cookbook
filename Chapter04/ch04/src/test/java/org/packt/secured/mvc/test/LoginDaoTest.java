package org.packt.secured.mvc.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.packt.secured.mvc.context.SpringDbConfig;
import org.packt.secured.mvc.dao.LoginDao;
import org.packt.secured.mvc.dispatcher.SpringDispatcherConfig;
import org.packt.secured.mvc.model.data.AccountLogin;
import org.packt.secured.mvc.model.data.Permission;
import org.packt.secured.mvc.model.data.Role;
import org.packt.secured.mvc.model.data.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig.class, SpringDispatcherConfig.class })
public class LoginDaoTest {
	
	@Autowired
	private LoginDao loginDaoImpl;
	
	
	@Test
	public void testLoginDetails(){
		List<AccountLogin> accounts = loginDaoImpl.getUsers();
		for(AccountLogin account: accounts){
			System.out.println(account.getUsername());
		}
	}
	
	@Test
	public void testRoles(){
		List<Role> roles = loginDaoImpl.getUserRoles();
		for(Role role: roles){
			System.out.println(role.getName());
		}
	}
	
	@Test
	public void testPermissions(){
		List<Permission> perms = loginDaoImpl.getPermissions();
		for(Permission perm: perms){
			System.out.println(perm.getName());
		}
	}
	
	@Test
	public void testRolePermission(){
		Integer userId = 3;
		List<RolePermission> roleperm = loginDaoImpl.getUserGrantedAuthority(userId);
		for(RolePermission rp: roleperm){
			System.out.println(rp.getPermissionId());
			System.out.println(rp.getRoleId());
		}
		
		System.out.println(loginDaoImpl.getPermission(1).getName());
		System.out.println(loginDaoImpl.getUserRole(1).getName());
	}
	
	@Test
	public void getUser(){
		AccountLogin  login = loginDaoImpl.getUser("sjctrags");
		System.out.println(login.getUsername());
	}
	

}
