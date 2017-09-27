package org.packt.process.core.config;

import java.time.Duration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.SendTo;

import reactor.core.publisher.Flux;

@Configuration
public class EmpIdConverterConverter {
	
   /*
	@StreamListener(Processor.INPUT) 
	@SendTo(Processor.OUTPUT)
	public Integer verifyEmpString(String message) {
		System.out.println("first");
		Integer empid = null;
		try{
			empid = Integer.parseInt(message);
		} catch(Exception e){
		    empid = 0;	
		}
		 
		return empid;
	}
   */
	
	
	@StreamListener
	@Output(Processor.OUTPUT)
	public Flux<String> verifyEmpString(@Input(Processor.INPUT) Flux<String> id) {
		System.out.println("first");
		id.delayElements(Duration.ofMillis(2))
		   .log();
		return id;
	}
	
}
