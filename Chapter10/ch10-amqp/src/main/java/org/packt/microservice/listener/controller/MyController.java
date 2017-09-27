package org.packt.microservice.listener.controller;

import org.packt.microservice.listener.config.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController  
public class  MyController {   
  
    @Autowired  
    private Sender sender;  
      
    /** 
     Sending a test message queue * 
     */  
   
    @RequestMapping(value = "/rabbitmq", method = RequestMethod.GET)  
    public String addEntity() {  
        sender.send("jkljklkjljjkljl");  
        return "hello world";  
    }  
    
    @GetMapping("/test")
    public String test(){
    	return "test";
    }
      
}  
