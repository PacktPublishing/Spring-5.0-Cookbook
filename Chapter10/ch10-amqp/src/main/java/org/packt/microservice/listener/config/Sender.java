package org.packt.microservice.listener.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component  
@RepositoryEventHandler
public class  Sender {   
	
	 @Autowired
	 CachingConnectionFactory rabbitConnectionFactory;
  
	private RabbitTemplate rabbitTemplate;
    private Queue candidateQueue;
    
    public Sender(RabbitTemplate rabbitTemplate, Queue candidateQueue) {
		this.rabbitTemplate = rabbitTemplate;
		this.candidateQueue = candidateQueue;
	}
    
    @HandleAfterCreate
    public void handleCandidateSave(String content) {
        send(content);
    }
  
    public void send(String content) {  
        System.out.println("Sender : " + content);  
        this.rabbitTemplate.convertAndSend("hello", content);  
    }  
}  