package org.packt.microservice.listener.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
@EnableRabbit
public class WebFluxConfig {
	
	
	
	@Bean
    public Queue candidateQueue() {
        return new Queue("hello");
    }
	
	

	
	
    

}
