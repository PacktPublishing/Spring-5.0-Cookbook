package org.packt.microservice.core.controller;

import java.util.List;

import org.packt.microservice.core.model.data.UserDetails;
import org.packt.microservice.core.service.UserdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginBlockingController {
	
	@Autowired
	private UserdetailsService userdetailsServiceImpl;
	
	
	@GetMapping("/userList")
	public List<UserDetails> exposeJpaUsers() {
		return userdetailsServiceImpl.findAllUserdetails();
	}

}
