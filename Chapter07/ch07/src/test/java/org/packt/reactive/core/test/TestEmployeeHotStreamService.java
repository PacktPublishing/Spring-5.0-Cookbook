package org.packt.reactive.core.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.reactive.codes.config.SpringDbConfig;
import org.packt.reactive.codes.dispatch.SpringDispatcherConfig;
import org.packt.reactive.codes.service.EmployeeHotStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig
		.class, SpringDispatcherConfig.class })
public class TestEmployeeHotStreamService {
	
	@Autowired
	private EmployeeHotStreamService employeeHotStreamServiceImpl;
	
	@Test
	public void testMonoProcessor(){
		employeeHotStreamServiceImpl.monoProcessorGetEmployee(11);	
	}
	
	@Test
	public void testFluxProcessor(){
		List<Integer> ids = Arrays.asList(1,15,11);
		employeeHotStreamServiceImpl.fluxProcessorGetEmployee(ids);
		
	}
	
	@Test
	public void testReplayProcessor(){
		List<String> names = Arrays.asList("Sherwin", "Marwin", "John", "Johnwin", "Jolina", "Owin");
		employeeHotStreamServiceImpl.validateNamesReplay(names);
		
	}
	
	@Test
	public void testTopicProcessor(){
		List<String> names = Arrays.asList("Sherwin", "Marwin", "John", "Johnwin", "Jolina", "Owin");
		employeeHotStreamServiceImpl.validateNamesTopic(names);
		
	}
	
	@Test
	public void testWorkQueueProcessor(){
		//List<String> names = Arrays.asList("Sherwin", "Marwin", "John", "Johnwin", "Jolina", "Owin");
		//employeeHotStreamServiceImpl.validateNamesWorkQueue(names);
		List<String> names = Arrays.asList("Sherwin", "Marwin", "John", "Johnwin", "Jolina", "Owin");
		employeeHotStreamServiceImpl.validateNamesUnicast(names);
		
	}
	
	@Test
	public void testConnectFluxProcessor(){	
		employeeHotStreamServiceImpl.freeFlowEmps().connect();	
	}
}
