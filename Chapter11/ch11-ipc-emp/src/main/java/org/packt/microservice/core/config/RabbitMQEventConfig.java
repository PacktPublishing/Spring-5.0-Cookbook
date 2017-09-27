package org.packt.microservice.core.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;


@Configuration
@EnableWebFlux
@EnableRabbit
public class RabbitMQEventConfig {
	
	 @Bean
	    public Queue queue() {
	        return new Queue("login.packt.retrieval.msg");
	    }

}
