package org.packt.microservice.instance.controller;

import org.packt.microservice.instance.model.data.LoginDetails;
import org.packt.microservice.instance.model.data.UserDetails;
import org.packt.microservice.instance.service.LogindetailsService;
import org.packt.microservice.instance.service.UserdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class RestServiceController {
	
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
