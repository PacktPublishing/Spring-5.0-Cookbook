package org.packt.microservice.core.amqp;

import org.packt.microservice.core.model.data.LoginDetails;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;

@Service  
@RepositoryEventHandler
public class SendRequestEventLogin {
	
	private RabbitTemplate rabbitTemplate;
	private Queue queue;
  
    
    public SendRequestEventLogin(Queue queue, RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.queue = queue;
	}
  
    @HandleAfterCreate
    public LoginDetails loginHandler(String content) {
        return send(content);
    }
    
    public LoginDetails send(String content) {  
        System.out.println("send request");
        LoginDetails results = (LoginDetails) rabbitTemplate.convertSendAndReceive(
        		queue.getName(), content); 
    	return results;
    } 
}
