package org.packt.process.source;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class HelloWorldSource {
	
	
	 // @InboundChannelAdapter(channel = Source.OUTPUT)
      public String sayHello() {
	         return "Hello, World!";
	    }
	  
	

}
