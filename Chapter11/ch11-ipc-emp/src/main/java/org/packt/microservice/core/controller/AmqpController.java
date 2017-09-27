package org.packt.microservice.core.controller;

import org.packt.microservice.core.amqp.SendAsyncEventLogin;
import org.packt.microservice.core.amqp.SendRequestEventLogin;
import org.packt.microservice.core.model.data.LoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AmqpController {
	
	@Autowired
	private SendRequestEventLogin sendRequestEventLogin;
	
	@Autowired
	private SendAsyncEventLogin sendAsyncEventLogin;
		
	@GetMapping(value="/amqpLoginDetail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public LoginDetails exposeGetLoginDetails(@PathVariable("id") String id) {
		return sendRequestEventLogin.send(id);
	}
	
	@GetMapping(value="/amqpLoginAsync/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public DeferredResult<LoginDetails> exposeGetLoginAsync(@PathVariable("id") String id) {
		return sendAsyncEventLogin.send(id);
	}
	
}
