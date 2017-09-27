package org.packt.nosql.mongo.core.service;

import org.packt.nosql.mongo.core.model.data.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
	
	public Flux<Employee> getAllEmps();
	public Flux<Employee> getAllEmps(Flux<Long> ids);
	
	public Mono<Employee> getEmpByid(Long id);
	
	public Flux<Employee> getEmpsByFname(String fname);
	public Flux<Employee> getEmpsByLname(String lname);
	
	public void saveEmp(Employee emp);
	public void saveEmps(Flux<Employee> emps);

}
