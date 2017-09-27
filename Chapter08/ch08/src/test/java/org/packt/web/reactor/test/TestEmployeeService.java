package org.packt.web.reactor.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.web.reactor.config.SpringAsynchConfig;
import org.packt.web.reactor.config.SpringDbConfig;
import org.packt.web.reactor.config.SpringScheduledConfig;
import org.packt.web.reactor.dispatch.SpringDispatcherConfig;
import org.packt.web.reactor.model.data.Employee;
import org.packt.web.reactor.model.form.EmployeeForm;
import org.packt.web.reactor.service.EmployeeService;
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
		form.setFirstName("Johnny");
		form.setLastName("Refamonte");
		form.setAge(38);
		form.setBirthday(new Date(83, 6, 25));
		form.setEmail("sjctrags@gmail.com");
		form.setEmpId(222222);
		form.setDeptId(362);
		employeeServiceImpl.addEmployee(form);
	}
	
	@Test
	public void testReadEmployees(){
		List<Employee> emps = null;
		try {
			emps = employeeServiceImpl.readEmployees().get(50000, TimeUnit.MILLISECONDS);
			for(Employee emp : emps){
				System.out.println(emp.getLastName());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testReadEmployeesCall(){
		
		Future<List<Employee>> future = new FutureTask<>(employeeServiceImpl.readEmployeesCall());
		try {
			System.out.println(future.get(5000, TimeUnit.MILLISECONDS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testReadOneEmp(){
		try {
			Employee emp = employeeServiceImpl.readEmployee(14).get();
			System.out.println(emp.getLastName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		employeeServiceImpl.delEmployee(66666);
	}
	
}
