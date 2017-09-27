package org.packt.microservice.core.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

//@Configuration
//@EnableWebFlux
//@EnableRabbit
public class RabbitMQConfig {
	
	 
	 @Bean
	 public DirectExchange exchange() {
	       return new DirectExchange("login.packt");
	 }
	 
	
}
