package org.packt.reactive.codes.service;

import java.util.List;

import org.packt.reactive.codes.model.data.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeBatchStreamService {
	
	public Flux<Employee> getDeferredEmployees();
	public Flux<Employee> selectSomeEmpRecords();
	public Mono<Employee> selectOneEmployee();
	public Flux<List<Employee>> getEmployeesByBatch();
	public Flux<String>   getTimedFirstNames();
	public Flux<Employee> selectEmpDelayed();
	

}
