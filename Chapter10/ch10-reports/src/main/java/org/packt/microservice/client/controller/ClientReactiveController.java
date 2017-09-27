package org.packt.microservice.client.controller;

import java.sql.Date;

import org.packt.microservice.client.model.data.CountDept;
import org.packt.microservice.client.model.data.Department;
import org.packt.microservice.client.model.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ClientReactiveController {
	
	// From Tomcat server
	@RequestMapping("/reactEmps")
	 public Flux<Employee> sayFlux() {
	           return WebClient.create().method(HttpMethod.GET)
	        		   .uri("http://localhost:8091/ch10-emp/selectReactEmps")
	        		   .contentType(MediaType.APPLICATION_JSON_UTF8).retrieve().bodyToFlux(Employee.class);
	 }
	
	@RequestMapping("/reactSelectEmps/{id}")
	public Flux<Employee> sayFlux(@PathVariable("id") Integer id) {
	           return WebClient.create().method(HttpMethod.GET)
	        		   .uri("http://localhost:8091/ch10-emp/selectReactEmp/" + id)
	        		   .contentType(MediaType.APPLICATION_JSON_UTF8).retrieve().bodyToFlux(Employee.class);
	 }
	
	// From Netty Server
	@RequestMapping("/reactFuncEmps")
	@ResponseBody
	public Flux<Employee> sayhandler() {
	           return WebClient.create().method(HttpMethod.GET)
	        		   .uri("http://localhost:8908/listFluxEmps")
	        		   .contentType(MediaType.APPLICATION_JSON_UTF8).retrieve().bodyToFlux(Employee.class);
	 }
	
	@RequestMapping("/reactCountDept")
	@ResponseBody
	public Mono<CountDept> count() {
	           return WebClient.create().method(HttpMethod.GET)
	        		   .uri("http://localhost:8901/countFluxDepts")
	        		   .contentType(MediaType.APPLICATION_OCTET_STREAM).retrieve().bodyToMono(CountDept.class);
	}
	
	@RequestMapping(value="/reactSaveEmp", consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String hello(@RequestBody Employee employee){
		 Mono<ClientResponse> resp = WebClient.create().post().uri("http://localhost:8908/saveEmp").accept(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromObject(employee)).exchange();
		 return resp.block().statusCode().name();
	 }
}
