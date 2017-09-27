package org.packt.spring.boot.controller;

import java.util.Arrays;
import java.util.List;

import org.packt.spring.boot.model.data.Employee;
import org.packt.spring.boot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class RestServiceController {
	
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
	
	@GetMapping("/fluxJpaEmps")
	public Flux<Employee> exposeJpaEmps() {
		return Flux.fromIterable(employeeServiceImpl.findAllEmps());
	}
	
	@PostMapping(path = "/fluxAddEmp", consumes = MediaType.APPLICATION_JSON_VALUE) 
	public void addMonoEmp(@RequestBody Mono<Employee> employee){
		
	}
	
	@PostMapping(path = "/fluxAddListEmps", consumes = MediaType.APPLICATION_JSON_VALUE) 
	public void addFluxEmp(@RequestBody Flux<Employee> employee){
		
	}

}
