package org.packt.nosql.mongo.core.controller;

import java.util.Arrays;
import java.util.List;

import org.packt.nosql.mongo.core.model.data.Department;
import org.packt.nosql.mongo.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		return departmentServiceImpl.getAllDepts();
	}
	
	@GetMapping("/selectReactDept/{id}")
	public Mono<Department> selectReactDept(@PathVariable("id") Long id) {
		return departmentServiceImpl.getDeptByid(id);
	}
	
	
	
	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}
	
	
	/*
	@PostMapping("/saveReactDept")
	public Mono<Void> saveReactDept(@RequestBody Department dept) {
		 return Mono.justOrEmpty(dept).doOnNext(departmentServiceImpl::saveDeptRec).then();
	}
	
	*/
}
