package org.packt.microservice.core.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

// Enable if not event-driven opton
//@Configuration
//@EnableWebFlux
//@EnableRabbit
public class RabbitMQConfig {
	
	@Bean
    public Queue queue() {
        return new Queue("login.packt.retrieval.msg");
    }
	
	 @Bean
	 public DirectExchange exchange() {
	        return new DirectExchange("login.packt");
	 }
	
		    
	@Bean
	public Binding bindingAsync(DirectExchange exchange, Queue queue) {
	     return BindingBuilder.bind(queue).to(exchange).with("packt");
	}
	
	

}
