package org.packt.reactive.core.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.reactive.codes.config.SpringDbConfig;
import org.packt.reactive.codes.dispatch.SpringDispatcherConfig;
import org.packt.reactive.codes.service.EmployeeTransformDataStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig
		.class, SpringDispatcherConfig.class })
public class TestEmployeeTransformDataStream {
	
	@Autowired
	private EmployeeTransformDataStream employeeTransformDataStreamImpl;
	
	@Test
	public void testConcatWith(){
		List<String> names = Arrays.asList("Sherwin", "Marwin", "John", "Johnwin", "Jolina", "Owin");
		employeeTransformDataStreamImpl.concatWithNames(names).subscribe(System.out::println);
	}
	
	@Test
	public void testMergeWith(){
		List<String> names = Arrays.asList("Sherwin", "Marwin", "John", "Johnwin", "Jolina", "Owin");
		employeeTransformDataStreamImpl.mergeWithNames(names).subscribe(System.out::println);
	}
	
	@Test
	public void testZipWith(){
		List<String> names = Arrays.asList("John", "Johnwin", "Jolina", "Owin");
		employeeTransformDataStreamImpl.zipWithNames(names).subscribe((tuple) -> {
			System.out.println(tuple.getT1() + "-" + tuple.getT2());
		});
	}
	
	@Test
	public void testGroupBy(){
		List<String> names = Arrays.asList("John", "Johnwin", "Jolina", "Owin");
		employeeTransformDataStreamImpl.groupNames().subscribe((grp) ->{
			    grp.collectList().subscribe((list)->{
			    	System.out.println("Key: " + grp.key() + " " + list);
			    });
		
		});
	}
	
	@Test
	public void testFlatMap(){
		List<String> names = Arrays.asList("Sherwin", "Marwin", "John", "Johnwin", "Jolina", "Owin");
		employeeTransformDataStreamImpl.flatMapWithNames(names).subscribe(System.out::println);
	}

}
