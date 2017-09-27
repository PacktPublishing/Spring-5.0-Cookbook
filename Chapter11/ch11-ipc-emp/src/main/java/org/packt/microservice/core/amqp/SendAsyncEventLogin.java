package org.packt.microservice.core.amqp;

import org.packt.microservice.core.model.data.LoginDetails;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.context.request.async.DeferredResult;

@Service
@RepositoryEventHandler
public class SendAsyncEventLogin {

	private AsyncRabbitTemplate asyncRabbitTemplate;
	private Queue requestQueue;

	public SendAsyncEventLogin(Queue requestQueue, AsyncRabbitTemplate rabbitTemplate) {
		this.asyncRabbitTemplate = rabbitTemplate;
		//this.asyncRabbitTemplate.setReceiveTimeout(1000);
		this.requestQueue = requestQueue;
	}

	@HandleAfterCreate
	public DeferredResult<LoginDetails> loginHandler(String content) {
		return send(content);
	}

	public DeferredResult<LoginDetails> send(String content) {
		System.out.println("send request");
		final DeferredResult<LoginDetails> response = new DeferredResult<>();
		ListenableFuture<LoginDetails> future = asyncRabbitTemplate.convertSendAndReceive(requestQueue.getName(),
				content);
		future.addCallback(new LoginHandlerResponse(response));

		System.out.println(asyncRabbitTemplate.isAutoStartup());
		System.out.println(asyncRabbitTemplate.isRunning());

		return response;
	}

	private static class LoginHandlerResponse implements ListenableFutureCallback<LoginDetails> {
		private DeferredResult<LoginDetails> result;

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
