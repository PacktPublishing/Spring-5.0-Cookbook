package org.packt.microservice.core.amqp;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.packt.microservice.core.model.data.LoginDetails;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskDecorator;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.context.request.async.DeferredResult;

//@Service  
public class SendAsyncLogin {
	
	@Autowired
	private DirectExchange exchange;
  
	private AsyncRabbitTemplate asyncRabbitTemplate;
	   
	public SendAsyncLogin(AsyncRabbitTemplate rabbitTemplate) {
		this.asyncRabbitTemplate = rabbitTemplate;
	  //this.asyncRabbitTemplate.setReceiveTimeout(1000);
	}
    
    @HandleAfterCreate
    public DeferredResult<LoginDetails> handleCandidateSave(String content) {
        return send(content);
    }
    
	public DeferredResult<LoginDetails> send(String content) {  
        System.out.println("send request");
        final DeferredResult<LoginDetails> response = new DeferredResult<>();    
        ListenableFuture<LoginDetails> future =
        		asyncRabbitTemplate.convertSendAndReceive(exchange.getName(), "packt.async", content);
        future.addCallback(new LoginHandlerResponse(response));
            
        System.out.println(asyncRabbitTemplate.isAutoStartup());
        System.out.println(asyncRabbitTemplate.isRunning());
		
    	return response;
    } 
	
	private static class LoginHandlerResponse implements ListenableFutureCallback<LoginDetails> {
        private  DeferredResult<LoginDetails> result;

        public LoginHandlerResponse(DeferredResult<LoginDetails> result) {
            this.result = result;
        }

        @Override
        public void onFailure(Throwable throwable) {
            result.setResult(new LoginDetails());
        }

        @Override
        public void onSuccess(LoginDetails response) {
            result.setResult(response);
        }
    }
	

}
