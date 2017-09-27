package org.packt.reactive.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.reactive.codes.config.SpringDbConfig;
import org.packt.reactive.codes.dispatch.SpringDispatcherConfig;
import org.packt.reactive.codes.model.data.Employee;
import org.packt.reactive.codes.service.EmployeeStreamService;
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
public class TestEmployeeStreamService {

	@Autowired
	private EmployeeStreamService employeeStreamServiceImpl;
	
	@Test
	public void testStreamThread(){
		Subscriber<Void> mySubscription = new Subscriber<Void>() {

			@Override
			public void onComplete() {
				System.out.println("-------------------End of Stream --------------------");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("-------------------Transmission Error --------------------");
			}
		
			@Override
			public void onSubscribe(Subscription subs) {}

			@Override
			public void onNext(Void none) {	}

		};
		
		employeeStreamServiceImpl.showThreads().subscribe(mySubscription);
	}
	
	@Test
	public void testReadEmployees(){
		
		Subscriber<Employee> mySubscription = new Subscriber<Employee>() {

			@Override
			public void onComplete() {
				System.out.println("-------------------End of Stream --------------------");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("-------------------Transmission Error --------------------");
			}

			@Override
			public void onNext(Employee emp) {
				System.out.format("%d %s %s %d \n", emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getAge());
			}

			@Override
			public void onSubscribe(Subscription subs) {
				subs.request(Long.MAX_VALUE);
			}

		};
		
		employeeStreamServiceImpl.readEmployees().subscribe(mySubscription);
	}
	
	@Test
	public void testReadSingleEmployee(){
		Subscriber<Employee> mySubscription = new Subscriber<Employee>() {

			@Override
			public void onComplete() {
				System.out.println("-------------------End of Stream --------------------");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("-------------------Transmission Error --------------------");
			}

			@Override
			public void onNext(Employee emp) {
				System.out.format("%d %s %s %d \n", emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getAge());

			}

			@Override
			public void onSubscribe(Subscription subs) {
				subs.request(Long.MAX_VALUE);

			}

		};
		
		employeeStreamServiceImpl.readEmployee(14).subscribe(mySubscription);
	}
	
	
	
	@Test
	public void testGetValidEmployees(){
		Subscriber<String> mySubscription = new Subscriber<String>() {

			@Override
			public void onComplete() {	}

			@Override
			public void onError(Throwable e) {	}

			@Override
			public void onNext(String name) {
				System.out.format("Employee: %s \n", name);
			}

			@Override
			public void onSubscribe(Subscription subs) {
				subs.request(Long.MAX_VALUE);

			}

		};
		
		employeeStreamServiceImpl.getValidEmployees().subscribe(mySubscription);
	}
	
	
	
}
