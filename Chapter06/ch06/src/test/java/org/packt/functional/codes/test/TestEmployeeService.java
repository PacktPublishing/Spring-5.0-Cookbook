package org.packt.functional.codes.test;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.functional.codes.config.SpringDbConfig;
import org.packt.functional.codes.dispatcher.SpringDispatcherConfig;
import org.packt.functional.codes.model.data.Employee;
import org.packt.functional.codes.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig.class, SpringDispatcherConfig.class })
public class TestEmployeeService {
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@Test
	public void testComputeSalary(){
		System.out.println(employeeService.updateSalary(2000, 500));
	}
	
	@Test
	public void testShowEmployees(){
		Iterator<Employee> iterate = employeeService.getEmployees().iterator();
		while(iterate.hasNext()){
			Employee temp = iterate.next();
			System.out.format("%s \n", temp.getFirstName());
		}
	}
	
	
	
	
	
	
	@Test
	public void testQualifiedEmpsGroup(){
		Iterator<Employee> iterate = employeeService.getEmployeePerDept(359).iterator();
		while(iterate.hasNext()){
			Employee temp = iterate.next();
			System.out.format("%s \n", temp.getFirstName());
		}
	}
	
	@Test
	public void testQualifiedEmpsNewFunc(){
		Iterator<Employee> iterate = employeeService.getEmployeesFunc().iterator();
		while(iterate.hasNext()){
			Employee temp = iterate.next();
			System.out.format("%s \n", temp.getFirstName());
		}
	}
	
	@Test
	public void testEmployeeRec(){
		Employee emp = employeeService.getEmployee(11111);
		System.out.format("%s \n", emp.getFirstName());
	}
	
	
	@Test
	public void testAverageAge(){
		double avgAge = employeeService.getAverageAge();
		System.out.format("%f \n", avgAge);
	}
	
	@Test
	public void testShowQualifications(){
		employeeService.printEmployeeNotQuaified();
	}
	
	
	
}
