package org.packt.reactive.codes.service.impl;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

import org.packt.reactive.codes.dao.EmployeeDao;
import org.packt.reactive.codes.model.data.Employee;
import org.packt.reactive.codes.service.EmployeeParallelStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.scheduler.Schedulers;

@Service
public class EmployeeParallelStreamServiceImpl implements EmployeeParallelStreamService{
	
	@Autowired
	private EmployeeDao employeeDaoImpl;

	@Override
	public Flux<String> parallelEmployeeNames() {
		Function<Employee, String> names = (emp) ->{
			System.out.println("flatMap thread: " + Thread.currentThread().getName());
			return emp.getFirstName().charAt(0) + emp.getLastName();
		};
		
		Flux<String> parallelEmpFlux = Flux.fromIterable(employeeDaoImpl.getEmployees())
        .parallel(8) 
        .runOn (Schedulers.parallel())
        .sequential()
        .map(names);
        
		return parallelEmpFlux;
	}

	@Override
	public Flux<GroupedFlux<Integer, Integer>> parallelGrpAvg() {
		Function<Employee, Integer> ages = (emp) ->{
			System.out.println("flatMap thread: " + Thread.currentThread().getName());
			return emp.getAge();
		};
		
		Flux<GroupedFlux<Integer, Integer>> parallelEmpFlux = Flux.fromIterable(employeeDaoImpl.getEmployees())
	        .delaySubscription(Duration.of(500L, ChronoUnit.MILLIS))
	        .parallel(8) 
	        .runOn (Schedulers.parallel())
	        .map(ages)
	        .groups();
        return parallelEmpFlux;
	}

	@Override
	public Flux<String> repeatExecs() {
		Function<Employee, String> names = (emp) ->{
			System.out.println("flatMap thread: " + Thread.currentThread().getName());
			return emp.getFirstName().charAt(0) + emp.getLastName();
		};
		
		Flux<String> parallelEmpFlux = Flux.fromIterable(employeeDaoImpl.getEmployees())
        .repeat(2)
		.parallel(8) 
        .runOn (Schedulers.parallel())
        .sequential()
        .map(names)
        .doOnSubscribe(subscription ->
        {
            subscription.request(2);
            System.out.println(subscription);
        });
        
		return parallelEmpFlux;
	}

	

}
