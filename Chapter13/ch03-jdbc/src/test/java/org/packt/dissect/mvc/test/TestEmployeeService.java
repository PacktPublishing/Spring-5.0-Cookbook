package org.packt.dissect.mvc.test;

import static org.mockito.BDDMockito.given;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.packt.dissect.mvc.context.SpringDbConfig;
import org.packt.dissect.mvc.dao.impl.EmployeeDaoImpl;
import org.packt.dissect.mvc.dispatcher.SpringDispatcherConfig;
import org.packt.dissect.mvc.model.data.Employee;
import org.packt.dissect.mvc.service.impl.EmployeeServiceImpl;
import org.packt.dissect.mvc.webxml.SpringWebInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringWebInitializer.class, SpringDbConfig.class, SpringDispatcherConfig.class })
public class TestEmployeeService {
	
	
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Mock
	private EmployeeDaoImpl employeeDaoImpl;
	
	@Before
	public void setUp(){
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testService(){
		
		List<Employee> emps = new ArrayList<>();
		
		Employee rec1 = new Employee();
		rec1.setId(22);
		rec1.setEmpId(3673);
		rec1.setAge(22);
		rec1.setBirthday(new Date(101,11,1));
		rec1.setDeptId(555);
		rec1.setFirstName("Joanna");
		rec1.setLastName("Kiko");
		
		emps.add(rec1);
		
		given(employeeDaoImpl.getEmployees()).willReturn(emps);
		System.out.println(employeeServiceImpl.readEmployees());
	}
}
