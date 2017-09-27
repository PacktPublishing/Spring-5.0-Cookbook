package org.packt.process.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
public class SyncBatchBootApplication {
	private static final Logger log = LoggerFactory.getLogger(SyncBatchBootApplication.class);
  
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SyncBatchBootApplication.class, args);
    }
}