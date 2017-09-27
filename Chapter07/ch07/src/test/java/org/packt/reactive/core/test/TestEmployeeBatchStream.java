package org.packt.reactive.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.reactive.codes.config.SpringDbConfig;
import org.packt.reactive.codes.dispatch.SpringDispatcherConfig;
import org.packt.reactive.codes.model.data.Employee;
import org.packt.reactive.codes.service.EmployeeBatchStreamService;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig
		.class, SpringDispatcherConfig.class })
public class TestEmployeeBatchStream {
	
	@Autowired
	private EmployeeBatchStreamService employeeBatchStreamServiceImpl;
	
	@Test
	public void testDeferred(){
		employeeBatchStreamServiceImpl.getDeferredEmployees().subscribe(System.out::println);
	}
	
	@Test
	public void testBatch(){
		employeeBatchStreamServiceImpl.getEmployeesByBatch().subscribe(System.out::println);
	}

	
	@Test
	public void testTimedFirstNames(){
		employeeBatchStreamServiceImpl.getTimedFirstNames().subscribe(new Subscriber<String>(){

			@Override
			public void onComplete() {	}

			@Override
			public void onError(Throwable arg0) {
				System.out.println("time is out....");
			}

			@Override
			public void onNext(String data) {
				System.out.println(data);
			}

			@Override
			public void onSubscribe(Subscription subs) {
				subs.request(Long.MAX_VALUE);
			}
			
		});
	}
	
	@Test
	public void testDelayed(){
		employeeBatchStreamServiceImpl.selectEmpDelayed().subscribe(System.out::println);
	}
	
	@Test
	public void testByRequest(){
		Subscriber<Employee> subscriber = new Subscriber<Employee>(){
			
			

			@Override
			public void onComplete() {	}

			@Override
			public void onError(Throwable arg0) {	}

			@Override
			public void onNext(Employee emp) {
				System.out.println(emp);
				
			}

			@Override
			public void onSubscribe(Subscription subs) {
				subs.request(1);
			}
		};
		employeeBatchStreamServiceImpl.selectSomeEmpRecords().subscribe(subscriber);
	}
	
	@Test
	public void testMono(){
		employeeBatchStreamServiceImpl.selectOneEmployee().subscribe(System.out::println);
	}
	
}
