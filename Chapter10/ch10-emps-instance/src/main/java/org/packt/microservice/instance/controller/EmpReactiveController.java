package org.packt.microservice.instance.controller;

import java.util.Arrays;
import java.util.List;

import org.packt.microservice.instance.model.data.Employee;
import org.packt.microservice.instance.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmpReactiveController {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	@RequestMapping("/objectSampleRest")
	public String exposeString() {
		return "Hello World";
	}
	
	@GetMapping("/monoSampleRest")
	public Mono<String> exposeMono() {
		return Mono.just("Hello World");
	}
	
	
	@GetMapping("/fluxSampleRest")
	public Flux<String> exposeFlux() {
		List<String> names = Arrays.asList("Anna", "John", "Lucy");
		return Flux.fromIterable(names).map((str) -> str.toUpperCase() + "---");
	}
	
	@GetMapping("/selectReactEmps")
	public Flux<Employee> selectReactEmps() {
		return Flux.fromIterable(employeeServiceImpl.findAllEmps());
	}
	

	@GetMapping("/selectReactEmp/{id}")
	public Mono<Employee> selectReactEmpDetail(@PathVariable("id") Integer id) {
		return Mono.justOrEmpty(employeeServiceImpl.findEmployeeByid(id)).defaultIfEmpty(new Employee());
	}
	
	@PostMapping("/saveReactEmp")
	public Mono<Void> saveReactEmpDetail(@RequestBody Employee dept) {
		 return Mono.justOrEmpty(dept).doOnNext(employeeServiceImpl::saveEmployeeRec).then();
	}
	
	
	
	


}
