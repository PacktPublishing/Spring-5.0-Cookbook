package org.packt.aop.transaction.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.aop.transaction.config.SpringDbConfig;
import org.packt.aop.transaction.dispatcher.SpringDispatcherConfig;
import org.packt.aop.transaction.model.data.Employee;
import org.packt.aop.transaction.model.form.EmployeeForm;
import org.packt.aop.transaction.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig
		.class, SpringDispatcherConfig.class })
public class TestEmployeeService {

	@Autowired
	private EmployeeService employeeServiceImpl;
	
	@Test
	public void testPersistEmployee(){
		EmployeeForm form = new EmployeeForm();
		form.setFirstName("Sherwin");
		form.setLastName("Tragura");
		form.setAge(38);
		form.setBirthday(new Date(78, 10, 30));
		form.setEmail("sjctrags@gmail.com");
		form.setEmpId(11111);
		form.setDeptId(359);
		employeeServiceImpl.addEmployee(form);
	}
	
	@Test
	public void testReadEmployees(){
		List<Employee> emps = employeeServiceImpl.readEmployees();
		assertNotNull(emps);
		
	}
	
	@Test
	public void testReadOneEmp(){
		Employee emp = employeeServiceImpl.readEmployee(10);
	}
	
	@Test
	public void testUpdateEmp(){
		EmployeeForm form = new EmployeeForm();
		
		form.setAge(56);
		form.setBirthday(new Date(60,10,10));
		form.setDeptId(6666);
		form.setEmail("sjctrags@yahoo.com");
		form.setFirstName("Sher");
		form.setLastName("Trags");
		form.setEmpId(359);
		employeeServiceImpl.updateEmployee(form, 11);
		
	}
	
	@Test
	public void testDelEmp(){
		employeeServiceImpl.delEmployee(11111);
	}
	
	
	@Test
	public void testReadEmpMonitor(){
		Employee emp = employeeServiceImpl.readEmployee(11);
	}
}
