package org.packt.reactive.codes.service;

import org.packt.reactive.codes.model.data.Employee;
import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeStreamService {
	
	public Publisher<Employee> readEmployees();
	public Publisher<Employee> readEmployee(Integer empId);
	public void delEmployee(Integer empId);
	public Publisher<String> getFirstNamesFlow();
	
	public Publisher<String> getValidEmployees();
	
	public Mono<Double> getAveAge();
	public Mono<Employee> getEmptyMonoStream();
	public Flux<String> getFirstNames();
	
	public Flux<Employee> getEmptyFluxStream();
	
	public Flux<Double> getStandardSalary();
	public Publisher<Void> showThreads();
}
