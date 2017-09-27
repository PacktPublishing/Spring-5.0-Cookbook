package org.packt.process.core;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableBatchProcessing
@SpringBootApplication
@EnableScheduling
public class AsyncBatchBootApplication {
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(AsyncBatchBootApplication.class, args);
    }
}
