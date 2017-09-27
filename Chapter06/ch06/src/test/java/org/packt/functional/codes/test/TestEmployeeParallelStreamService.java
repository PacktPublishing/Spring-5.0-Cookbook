package org.packt.functional.codes.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.functional.codes.config.SpringDbConfig;
import org.packt.functional.codes.dispatcher.SpringDispatcherConfig;
import org.packt.functional.codes.service.impl.EmployeeParallelStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig.class, SpringDispatcherConfig.class })
public class TestEmployeeParallelStreamService {
	
	@Autowired
	private EmployeeParallelStreamService employeeParallelStreamService;
	
		
	@Test
	public void testParallelViewRecs(){
		System.out.println("************EXECUTION 1*************");
		employeeParallelStreamService.showAllEmployees();
		System.out.println("************EXECUTION 2*************");
		employeeParallelStreamService.showAllEmployees();
		System.out.println("************************************");
	}
	
	@Test
	public void compareComputation(){
		System.out.println("************PARALLEL****************");
		System.out.println("Average: " + employeeParallelStreamService.getParallelAverageAge());
		System.out.println("************SEQUENTIAL**************");
		System.out.println("Average: " + employeeParallelStreamService.getSequentialAverageAge());
		System.out.println("************************************");
	}

}
