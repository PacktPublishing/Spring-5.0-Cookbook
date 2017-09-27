package org.packt.microservice.core.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

//@Configuration
//@EnableWebFlux
//@EnableRabbit
public class RabbitMQConfigAsync {
	
	@Autowired
	private DirectExchange exchange;
	
	@Bean
    public Queue requestQueue() {
        return new Queue("msg.request");
    }

    @Bean
    public Queue replyQueue() {
    	return new Queue("msg.reply");
    }
	   
    @Bean
    public Binding binding(DirectExchange exchange, Queue requestQueue) {
        return BindingBuilder.bind(requestQueue).to(exchange).with("packt.async");
    }
   
   
}
