package org.packt.microservice.core.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
@EnableRabbit
public class RabbitMQEventConfigAsync {
	
	@Bean
    public Queue requestQueue() {
        return new Queue("msg.request");
    }

    @Bean
    public Queue replyQueue() {
    	return new Queue("msg.reply");
    } 
}
