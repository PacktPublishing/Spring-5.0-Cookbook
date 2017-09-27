package org.packt.nosql.mongo.core.controller;

import org.packt.nosql.mongo.core.model.data.Employee;
import org.packt.nosql.mongo.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmpReactiveController {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	@GetMapping("/selectReactEmps")
	public Flux<Employee> selectReactDepts() {
		return employeeServiceImpl.getAllEmps();
	}
	
	@GetMapping("/selectReactEmp/{id}")
	public Mono<Employee> selectReactDept(@PathVariable("id") Long id) {
		return employeeServiceImpl.getEmpByid(id);
	}
}
