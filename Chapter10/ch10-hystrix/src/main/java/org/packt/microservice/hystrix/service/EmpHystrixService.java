package org.packt.microservice.hystrix.service;

import java.util.concurrent.ExecutionException;

import org.packt.microservice.hystrix.model.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class EmpHystrixService {
	
	@Autowired
	private AsyncRestTemplate asyncRestTemplate;
	
	@HystrixCommand(fallbackMethod = "defaultSelectEmp")
	public Employee getAsyncEmp(Integer id){
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
	
	  private Employee defaultSelectEmp(Integer id) {
	    	
	        return new Employee() ;
	    }


}
