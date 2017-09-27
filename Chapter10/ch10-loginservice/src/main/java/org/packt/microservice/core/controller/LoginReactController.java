package org.packt.microservice.core.controller;

import org.packt.microservice.core.model.data.LoginDetails;
import org.packt.microservice.core.model.data.UserDetails;
import org.packt.microservice.core.service.LogindetailsService;
import org.packt.microservice.core.service.UserdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class LoginReactController {
	
	@Autowired
	private LogindetailsService logindetailsServiceImpl;
	
	@Autowired
	private UserdetailsService userdetailsServiceImpl;

	
	
	@GetMapping("/fluxJpaUsers")
	public Flux<UserDetails> exposeJpaUsers() {
		return Flux.fromIterable(userdetailsServiceImpl.findAllUserdetails());
	}
	
	@GetMapping("/fluxJpaLogins")
	public Flux<LoginDetails> exposeJpaLogins() {
		return Flux.fromIterable(logindetailsServiceImpl.findAllLogindetails());
	}
	
	
	
	

}
