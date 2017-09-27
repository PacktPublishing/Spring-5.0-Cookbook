package org.packt.microservice.hrs.controller;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.packt.microservice.hrs.model.data.Employee;
import org.packt.microservice.hrs.service.EmployeeService;
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
	
	// Reactor Framework part below.....
	
	@GetMapping("/fluxEmpsList")
	public Flux<Employee> exposeFluxEmpsList() {
		return null;
	}
	
	@GetMapping(path = "/fluxAddEmp") 
	public Flux<String> addMonoEmp(){
		Employee newEmp = new Employee();
    	newEmp.setEmpid(56758);
    	newEmp.setAge(43);
    	newEmp.setBirthday(new Date(88, 10,10));
    	newEmp.setDeptid(362);
    	newEmp.setEmail("aloha@gmail.com");
    	newEmp.setFirstname("John");
    	newEmp.setLastname("Lowell");
		employeeServiceImpl.saveEmployeeRec(newEmp);
		Flux<String> status = Flux.just("OK");
		return status;
	}
	
	@PostMapping(path = "/fluxSelectEmps", consumes = MediaType.APPLICATION_JSON_VALUE) 
	public List<Employee> exposeFluxEmps(@RequestBody Flux<Integer> ids){
		
		return null;
	}
	
	
	@PostMapping(path = "/fluxSelectById", consumes = MediaType.APPLICATION_JSON_VALUE) 
	public List<Employee> exposeEmpById(@RequestBody Integer id){
		return null;
	}

}
