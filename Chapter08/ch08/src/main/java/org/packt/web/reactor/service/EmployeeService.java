package org.packt.web.reactor.service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.packt.web.reactor.model.data.Employee;
import org.packt.web.reactor.model.form.EmployeeForm;
import org.springframework.web.context.request.async.DeferredResult;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
	
	public CompletableFuture<List<Employee>> readEmployees();
	public Callable<List<Employee>> readEmployeesCall();
	public Future<Employee>  readEmployee(Integer empId);
	public void addEmployee(EmployeeForm emp);
	public void updateEmployee(EmployeeForm emp, int id) ;
	public void delEmployee(Integer empId);
	
	
	public Flux<Employee> readEmployeesFlux(int age);
	public Flux<Employee> readEmployeesByDescAge();
	public Flux<Employee> readEmployeesByAscLastName();
	public Flux<String> readEmpFirstNames();
	public Mono<Double> getAveAge();
	
}
