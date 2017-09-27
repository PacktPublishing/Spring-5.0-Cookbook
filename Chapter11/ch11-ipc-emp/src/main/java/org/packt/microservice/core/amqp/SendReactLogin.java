package org.packt.microservice.core.amqp;

import org.packt.microservice.core.model.data.LoginDetails;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Flux;

//@Service  
public class SendReactLogin {
	
	 @Autowired
	 CachingConnectionFactory rabbitConnectionFactory;
	
	 @Autowired
	 DirectExchange exchangeAsync;
  
	private RabbitTemplate rabbitTemplate;
	//private Queue candidateQueue;
  
    
    //public SendRequestLogin(Queue candidateQueue, RabbitTemplate rabbitTemplate) {
	public SendReactLogin(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	//	this.candidateQueue = candidateQueue;
		
	}
    /*
    @HandleAfterCreate
    public LoginDetails handleCandidateSave(String content) {
        return send(content);
    }
    */
    
    public Flux<LoginDetails> send(String content) {  
        System.out.println("send request");
        Flux<LoginDetails> candidates = (Flux<LoginDetails>) rabbitTemplate.convertSendAndReceive(
        		exchangeAsync.getName(), "packt.react", content); 
    	return candidates;
    } 
    
     
   /*
    public LoginDetails send(String content) {  
        System.out.println("send request");
        LoginDetails candidates = (LoginDetails) rabbitTemplate.convertSendAndReceive(
        		candidateQueue.getName(), content); 
    	return candidates;
    } 
    
    */
    
   

}
