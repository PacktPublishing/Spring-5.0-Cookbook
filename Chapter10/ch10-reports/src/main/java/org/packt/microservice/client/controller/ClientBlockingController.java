package org.packt.microservice.client.controller;

import java.util.HashMap;
import java.util.Map;

import org.packt.microservice.client.model.data.Department;
import org.packt.microservice.client.model.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ClientBlockingController {
	
	@Autowired
	protected RestTemplate restTemplate;
		
	@RequestMapping("/blockingString")
	@ResponseBody
	 public String clientSelectEmps() {
	      return restTemplate.getForObject("http://localhost:8091/ch10-emp/objectSampleRest", String.class);
	 }
	
	@RequestMapping(value="/blockingEmployee/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	 public Employee clientSelectEmp(@PathVariable("id") Integer id) {
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
	    HttpEntity<String> entity = new HttpEntity<>(headers);
	    
	   	ResponseEntity<Employee> response = restTemplate.exchange("http://localhost:8091/ch10-emp/selectEmp/{id}" ,
               HttpMethod.GET, entity, Employee.class, id);
	           return response.getBody();
	 }
	
	@RequestMapping(value="/blockingSaveDept", method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String clientSaveDept(@RequestBody Department dept){
		try{
			restTemplate.postForObject( "http://localhost:8090/ch10-dept/saveDeptRec", dept, Department.class);
			return "OK";
		}catch(Exception e){
			return "NOT OK";
		}
	}
}
