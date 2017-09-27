package org.packt.microservice.core.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
@EnableRabbit
public class RabbitMQConfigAsync {

	@Autowired
	private CachingConnectionFactory rabbitConnectionFactory;

	@Bean
	public Queue requestQueue() {
		return new Queue("msg.request");
	}

	@Bean
	public Queue replyQueue() {
		return new Queue("msg.reply");
	}

	@Bean
	public SimpleMessageListenerContainer simpleMessageListenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitConnectionFactory);
		container.setQueueNames("msg.reply");
		return container;
	}

	@Bean
	public RabbitTemplate template() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory);
		rabbitTemplate.setRoutingKey("msg.request");
		return rabbitTemplate;
	}

	@Bean
	public AsyncRabbitTemplate asyncRabbitTemplate(RabbitTemplate rabbitTemplate,
			SimpleMessageListenerContainer container) {
		AsyncRabbitTemplate asyncRabbitTemplate = new AsyncRabbitTemplate(rabbitTemplate, container);
		asyncRabbitTemplate.setReceiveTimeout(10000);

		return asyncRabbitTemplate;
	}
}
