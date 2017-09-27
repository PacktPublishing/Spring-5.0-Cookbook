package org.packt.spring.boot.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

@EnableAsync
@Configuration
public class SpringAsynchConfig implements AsyncConfigurer {
	
	private static Logger logger = LoggerFactory.getLogger(SpringAsynchConfig.class);
	     
	       /*
	        @Override
			public Executor getAsyncExecutor() {
				ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			    executor.setCorePoolSize(5);
		        executor.setMaxPoolSize(9);
		        executor.setQueueCapacity(50);
		        executor.setThreadNamePrefix("Ch08Executor-");
		        executor.setWaitForTasksToCompleteOnShutdown(true);
		        executor.setKeepAliveSeconds(5000);
		        executor.setAwaitTerminationSeconds(1000);
		        executor.initialize();
		       
		        return executor;
			}
			*/
		
	        /*
	        @Override
	        public Executor getAsyncExecutor () {
	            SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
	            executor.setConcurrencyLimit(100);
	            return executor;
	        }
	        */
	        @Bean("mvcTaskexecutor")
			@Override
	        public Executor getAsyncExecutor() {
				ConcurrentTaskExecutor executor = new ConcurrentTaskExecutor(
	                    Executors.newFixedThreadPool(100));
		
				executor.setTaskDecorator(new TaskDecorator() {
	              @Override
	              public Runnable decorate (Runnable runnable) {
	                  return () -> {
         
	                      long t = System.currentTimeMillis();
	                      runnable.run();
	                      logger.info("creating ConcurrentTaskExecutor thread pool....");
	                      System.out.printf("Thread %s has a processing time:  %s%n", Thread.currentThread().getName(),       
	                                        (System.currentTimeMillis() - t));
	                  };
	              }
	          });
				return executor;
		}
}
