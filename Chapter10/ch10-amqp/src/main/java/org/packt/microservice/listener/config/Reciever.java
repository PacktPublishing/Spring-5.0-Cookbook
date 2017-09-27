package org.packt.microservice.listener.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component  
@RabbitListener(queues = "hello")  
public class Reciever {   
  
    @RabbitHandler  
    public void process(String content) {  
        System.out.println("Receiver : " + content);  
    }  
  
}  