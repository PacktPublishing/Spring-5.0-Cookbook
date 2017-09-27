package org.packt.microservice.client.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.packt.microservice.client.model.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Controller
public class AccessRestController {
	
	private String instanceEmp = "http://EMPS-SERVICE-INSTANCE";
	private String instanceDept = "http://DEPTS-SERVICE-INSTANCE";
		
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
	private AsyncRestTemplate asyncRestTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	
	
	@RequestMapping("/accessString")
	@ResponseBody
	public String clientSelectEmps() {
	      return restTemplate.getForObject(instanceEmp+"/objectSampleRest", String.class);
	 }
	
	
	@GetMapping(value="/accessListEmps", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Employee> blockListEmp() {
	     HttpHeaders headers = new HttpHeaders();
		 headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		 HttpEntity<String> entity = new HttpEntity<>(headers);
		 ResponseEntity<List> response = restTemplate.exchange(instanceEmp + "/listEmp", HttpMethod.GET, entity, List.class);
		 return response.getBody();
	}
	
	@GetMapping(value="/accessListDepts", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Employee> blockListDepts() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<List> response = restTemplate.exchange(instanceDept + "/listDept", HttpMethod.GET, entity, List.class);
		return response.getBody();
	}
		
	@RequestMapping(value="/asyncSelectEmp/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Employee asyncSelectEmps(@PathVariable("id") Integer id){
		String url = instanceEmp + "/callSelectEmp/{id}.json";
		HttpMethod method = HttpMethod.GET;
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> requestEntity = new HttpEntity<String>("params", headers);
		ListenableFuture<ResponseEntity<Employee>> future = asyncRestTemplate.exchange(url, method, requestEntity, Employee.class, id);
		try {
			ResponseEntity<Employee> entity = future.get();
			return entity.getBody();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	@RequestMapping("/accessReactClients")
	public Flux<Employee> sayFlux() {
		ServiceInstance serviceInstance=loadBalancer.choose("EMPS-SERVICE-INSTANCE");
		System.out.println(serviceInstance.getUri());
		String baseUrl=serviceInstance.getUri().toString();
		return WebClient.create().method(HttpMethod.GET)
	        	        .uri(baseUrl + "/selectReactEmps")
	        		    .contentType(MediaType.APPLICATION_JSON_UTF8).retrieve().bodyToFlux(Employee.class);
	 }
}
