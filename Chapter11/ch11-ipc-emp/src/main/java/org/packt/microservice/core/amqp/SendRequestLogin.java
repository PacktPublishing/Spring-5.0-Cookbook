package org.packt.microservice.core.amqp;

import java.util.List;

import org.packt.microservice.core.model.data.LoginDetails;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

//@Service  
public class SendRequestLogin {
	
	
	@Autowired
	private DirectExchange exchange;
 
	private RabbitTemplate rabbitTemplate;
	 
    
   
	public SendRequestLogin(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
			
	}
  
     
    
    public LoginDetails send(String content) {  
        System.out.println("send request");
        LoginDetails candidates = (LoginDetails) rabbitTemplate.convertSendAndReceive(
        		exchange.getName(), "packt", content); 
    	return candidates;
    } 
    
    
    
       
    

}
