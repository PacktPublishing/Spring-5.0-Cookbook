package org.packt.functional.codes.test;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.functional.codes.config.SpringDbConfig;
import org.packt.functional.codes.dispatcher.SpringDispatcherConfig;
import org.packt.functional.codes.model.data.Employee;
import org.packt.functional.codes.service.impl.EmployeeStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig.class, SpringDispatcherConfig.class })
public class TestEmployeeStreamService {
	
	@Autowired
	private EmployeeStreamService employeeStreamService;
	
	@Test
	public void testArrayEmps(){
		Employee[] emps = employeeStreamService.arrayStream();
		System.out.println(Arrays.toString(emps));
	}

	
	@Test
	public void testQualifiedEmps(){
		Iterator<Employee> iterate = employeeStreamService.getEmployeesAge().iterator();
		while(iterate.hasNext()){
			Employee temp = iterate.next();
			System.out.format("%s \n", temp.getFirstName());
		}
	}
	
	@Test
	public void testShowSortedEmployees(){
		Iterator<Employee> iterate = employeeStreamService.getSortedEmployees().iterator();
		while(iterate.hasNext()){
			Employee temp = iterate.next();
			System.out.format("%s \n", temp.getFirstName());
		}
	}
	
	@Test	
	public void testSetNames(){
		System.out.println(employeeStreamService.getDistinctNames());
	}
	
	@Test
	public void testShowUpdatedNames(){
		Iterator<Employee> iterate = employeeStreamService.updateNames().iterator();
		while(iterate.hasNext()){
			Employee temp = iterate.next();
			System.out.format("%s \n", temp.getFirstName());
		}
	}
	
	@Test
	public void showNewEmps(){
		employeeStreamService.createCustomArrayStream();
	}
	
	
}
