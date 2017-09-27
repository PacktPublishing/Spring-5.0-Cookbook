package org.packt.microservice.core.amqp;

import org.packt.microservice.core.model.data.LoginDetails;
import org.packt.microservice.core.service.LogindetailsService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

//@Service
//@RabbitListener(queues = "login.packt.retrieval.msg.react")  
public class RequestFluxLogins {
	
	@Autowired
	private LogindetailsService logindetailsServiceImpl;
	
	
	@RabbitHandler  
	public Flux<LoginDetails> process(String content) {  
	      Integer id = Integer.parseInt(content);
	      Flux<LoginDetails> details =Flux.just(logindetailsServiceImpl.findLoginById(id));
	      return details ;
	}  
	
	

}
