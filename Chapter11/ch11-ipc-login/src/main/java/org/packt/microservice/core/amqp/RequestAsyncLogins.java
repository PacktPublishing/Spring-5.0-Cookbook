package org.packt.microservice.core.amqp;

import org.packt.microservice.core.model.data.LoginDetails;
import org.packt.microservice.core.service.LogindetailsService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "msg.request") 
public class RequestAsyncLogins {
	
	@Autowired
	private LogindetailsService logindetailsServiceImpl;
	
	@RabbitHandler  
	public LoginDetails process(String content) {  
	      Integer id = Integer.parseInt(content);
	      LoginDetails details = logindetailsServiceImpl.findLoginById(id);
	      return details ;
	}  

}
