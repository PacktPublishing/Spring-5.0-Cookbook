package org.packt.reactive.core.test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.reactive.codes.config.SpringDbConfig;
import org.packt.reactive.codes.dispatch.SpringDispatcherConfig;
import org.packt.reactive.codes.service.EmployeeBatchStreamService;
import org.packt.reactive.codes.service.EmployeeStreamService;
import org.packt.reactive.codes.service.EmployeeTransformDataStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import reactor.test.StepVerifier;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig
		.class, SpringDispatcherConfig.class })
public class TestEmployeUsingVerifier {
	
	@Autowired
	private EmployeeStreamService employeeStreamServiceImpl;
	
	@Autowired
	private EmployeeTransformDataStream employeeTransformDataStreamImpl;
	
	@Autowired
	private EmployeeBatchStreamService employeeBatchStreamServiceImpl;
	
	@Test
	public void testEmpNames(){
		 StepVerifier.create(employeeStreamServiceImpl.getFirstNames())
	        .expectSubscription()
	        .expectNext("Sherwin")
	        .expectNext("Rey")
	        .thenCancel()
	        .log().verify();
	}
	
	@Test
	public void testEmpNamesVirtual(){
		StepVerifier.withVirtualTime(() -> employeeBatchStreamServiceImpl.getTimedFirstNames())
        .expectSubscription()
        .thenAwait(Duration.ofSeconds(2))
        .expectNext("Rey")
        .expectNext("Sherwin")
        .thenCancel()
        .log().verify();
	}
	
	@Test
	public void testOnComplete() {
		List<String> names = Arrays.asList("John", "Johnwin", "Jolina", "Owin");
		StepVerifier.create(employeeTransformDataStreamImpl.concatWithNames(names))
        .expectNext("Johnwin", "Owin", "Riza")
        .expectComplete()
        .verify();
	}
	
		
	
	@Test
	public void testGroup() {
		StepVerifier
				.create(employeeTransformDataStreamImpl.groupNames().blockFirst())
				.expectSubscription()
				.expectNext("joel")
				.expectNext("jerry")
				.expectComplete()
				.verify();
	}
}
