package org.packt.reactive.core.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.reactive.codes.config.SpringDbConfig;
import org.packt.reactive.codes.dispatch.SpringDispatcherConfig;
import org.packt.reactive.codes.model.data.Employee;
import org.packt.reactive.codes.service.EmployeeRxJavaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig
		.class, SpringDispatcherConfig.class })
public class TestEmployeeRxJavaService {
	
	@Autowired
	private EmployeeRxJavaService employeeRxJavaServiceImpl;
	
	@Test
	public void testEmployeeData(){
		Observer<Employee> mySubscription = new Observer<Employee>() {

			@Override
			public void onComplete() {
				System.out.println("subscription completed");
				
			}

			@Override
			public void onError(Throwable ex) {
				System.out.println("problems encountered" + ex.getMessage());
				
			}

			@Override
			public void onNext(Employee emp) {
				System.out.format("Employee: %s \n", emp.getEmpId());
			}

			@Override
			public void onSubscribe(Disposable arg0) {
				System.out.println("subscription started");
			}

		};
		employeeRxJavaServiceImpl.getEmployeesRx().subscribe(mySubscription);
	}
	
	@Test
	public void testEmployeeDataRx(){
		TestObserver<Employee> testObserver = new TestObserver<>();
		employeeRxJavaServiceImpl.getEmployeesRx().subscribe(testObserver);

		testObserver.assertNoErrors();
		List<Employee> emps = testObserver.values();
		for(Employee emp: emps){
			System.out.println(emp.getFirstName());
		}
	}
	
		
	
	@Test
	public void testEmployeeDataConsumer(){
		Consumer<Employee> consume = (emp) ->{
			System.out.println(emp.getFirstName());
		};
		employeeRxJavaServiceImpl.getEmployeesRx().subscribe(consume);
	}
	
	@Test
	public void testEmpHotStream(){
		employeeRxJavaServiceImpl.freeFlowEmps().connect();
	}

}
