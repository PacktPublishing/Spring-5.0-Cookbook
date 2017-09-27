package org.packt.microservice.core.amqp;

import java.util.List;

import org.packt.microservice.core.model.data.LoginDetails;
import org.packt.microservice.core.service.LogindetailsService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RabbitListener(queues = "login.packt.retrieval.msg")  
public class RequestListLogins {
	
	@Autowired
	private LogindetailsService logindetailsServiceImpl;
	
	
	@RabbitHandler  
	public LoginDetails process(String content) {  
	      Integer id = Integer.parseInt(content);
	      LoginDetails details = logindetailsServiceImpl.findLoginById(id);
	      return details ;
	}  

	/*
	@RabbitHandler  
	public String process(String content) {  
	    return content.toUpperCase();
	} 
	
	 */
}
