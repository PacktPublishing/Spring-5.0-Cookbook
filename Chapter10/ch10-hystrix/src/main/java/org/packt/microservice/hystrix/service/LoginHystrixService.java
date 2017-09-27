package org.packt.microservice.hystrix.service;

import java.util.ArrayList;
import java.util.List;

import org.packt.microservice.hystrix.model.data.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class LoginHystrixService {
	
	@Autowired
	protected RestTemplate restTemplate;
	
	 @HystrixCommand(fallbackMethod = "defaultUserDetails")
	 public List<UserDetails> getLoginUsers() {
	        return restTemplate
	          .getForObject("http://localhost:8092/ch10-login/fluxJpaUsers", 
	          List.class);
	 }
	 
	 private List<UserDetails> defaultUserDetails() {
		    List<UserDetails> emptyList = new ArrayList();
		    emptyList.add(new UserDetails());
	        return emptyList;
	 }

}
