package org.packt.microservice.instance.controller;

import java.util.Arrays;
import java.util.List;

import org.packt.microservice.instance.model.data.Department;
import org.packt.microservice.instance.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class DeptReactiveController {
	
	@Autowired
	private DepartmentService departmentServiceImpl;
		
	@GetMapping("/monoSampleRest")
	public Mono<String> exposeMono() {
		return Mono.just("Hello World");
	}
	
	
	@GetMapping("/fluxSampleRest")
	public Flux<String> exposeFlux() {
		List<String> names = Arrays.asList("Anna", "John", "Lucy");
		return Flux.fromIterable(names).map((str) -> str.toUpperCase() + "---");
	}
	
	@GetMapping("/selectReactDepts")
	public Flux<Department> selectReactDepts() {
		return Flux.fromIterable(departmentServiceImpl.findAllDepts());
	}
	
	@GetMapping("/selectReactDept/{id}")
	public Mono<Department> selectReactDept(@PathVariable("id") Integer id) {
		return Mono.justOrEmpty(departmentServiceImpl.findDeptByid(id)).defaultIfEmpty(new Department());
	}
	
	@PostMapping("/saveReactDept")
	public Mono<Void> saveReactDept(@RequestBody Department dept) {
		 return Mono.justOrEmpty(dept).doOnNext(departmentServiceImpl::saveDeptRec).then();
	}
}
