package org.packt.microservice.hystrix.service;

import org.packt.microservice.hystrix.model.data.Department;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import reactor.core.publisher.Mono;

@Service
public class DeptHystrixService {
	
	    @HystrixCommand(fallbackMethod = "defaultSelectDept")
	    public  Mono<Department> getMonoDept(Integer id) {
	        return WebClient.create().method(HttpMethod.GET)
	        		   .uri("http://localhost:8090/ch10-dept/selectReactDept/" + id)
	        		   .contentType(MediaType.APPLICATION_OCTET_STREAM).retrieve().bodyToMono(Department.class);
	    }
	 
	    private Mono<Department> defaultSelectDept(Integer id) {
	    	Mono<Department> blankDept = Mono.justOrEmpty(new Department());
	        return blankDept ;
	    }
}
