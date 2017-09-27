package org.packt.reactive.core.test;

import java.util.function.Consumer;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.reactive.codes.config.SpringDbConfig;
import org.packt.reactive.codes.dispatch.SpringDispatcherConfig;
import org.packt.reactive.codes.model.data.Employee;
import org.packt.reactive.codes.service.EmployeeScheduledStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig
		.class, SpringDispatcherConfig.class })
public class TestEmployeeScheduledStreamService {
	
	@Autowired
	private EmployeeScheduledStreamService employeeScheduledStreamServiceImpl;
	
	@Ignore
	@Test
	public void testElasticScheduler(){
		
		Consumer<Employee> viewThread = (e) ->{
			System.out.println(e+" : "+Thread.currentThread().getName());
			
		};
		employeeScheduledStreamServiceImpl.elasticFlow().subscribe(viewThread);
		
		while (true) {
	         try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
	}
	
	
	@Test
	public void testWindows(){
		employeeScheduledStreamServiceImpl.selectNamesScheduler().subscribe(System.out::println);
	}

}
