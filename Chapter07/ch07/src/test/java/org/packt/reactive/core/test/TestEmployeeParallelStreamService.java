package org.packt.reactive.core.test;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.reactive.codes.config.SpringDbConfig;
import org.packt.reactive.codes.dispatch.SpringDispatcherConfig;
import org.packt.reactive.codes.service.EmployeeParallelStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig
		.class, SpringDispatcherConfig.class })
public class TestEmployeeParallelStreamService {
	
	@Autowired
	private EmployeeParallelStreamService employeeParallelStreamServiceImpl;
	
	@Test
	public void testParallelEmpFlux(){

		Consumer<String> viewThread = (e) ->{
			System.out.println(e+" : "+Thread.currentThread().getName());
			
		};
		
		employeeParallelStreamServiceImpl.parallelEmployeeNames().subscribe(viewThread);
		
		try {
			TimeUnit.SECONDS.sleep ( 3L );
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Test
	public void testGroupParallelEmpFlux(){
		
		employeeParallelStreamServiceImpl.parallelGrpAvg().subscribe(grp ->
				grp.subscribe(d ->{
					System.out.println(d + " " + Thread.currentThread().getName() + " - " + grp.key());
           }));
		
		try {
			TimeUnit.SECONDS.sleep ( 3L );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
