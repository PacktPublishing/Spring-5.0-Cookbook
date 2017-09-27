package org.packt.aop.transaction.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.aop.transaction.config.SpringDbConfig;
import org.packt.aop.transaction.dispatcher.SpringDispatcherConfig;
import org.packt.aop.transaction.model.data.AccountLogin;
import org.packt.aop.transaction.service.EmployeeService;
import org.packt.aop.transaction.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig.class, SpringDispatcherConfig.class })
public class TestLoginService {

	@Autowired
	private LoginService loginServiceImpl;
	
	
	@Test
	public void testLoginUsers(){
		
		List<AccountLogin> users = loginServiceImpl.getUserAccounts();
		for(AccountLogin user: users){
			System.out.println(user.getUsername());
		}
		
	}
	
	@Test
	public void testGetUser(){
		AccountLogin user = loginServiceImpl.getUserAccount("sjctrags");
		System.out.println(user.getPassword());
	}
}
