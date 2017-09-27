package org.packt.microservice.client.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.packt.microservice.client.model.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.AsyncRestTemplate;

@Controller
public class ClientAsyncController {
	
	@Autowired
	private AsyncRestTemplate asyncRestTemplate;
	
	@RequestMapping(value="/asyncSelectEmp/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Employee asyncSelectEmps(@PathVariable("id") Integer id){
		String url ="http://localhost:8091/ch10-emp/callSelectEmp/{id}.json";
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

}
