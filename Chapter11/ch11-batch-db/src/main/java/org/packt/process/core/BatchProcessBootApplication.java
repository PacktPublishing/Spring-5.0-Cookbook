package org.packt.process.core;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableBatchProcessing
public class BatchProcessBootApplication  {
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(BatchProcessBootApplication.class, args);
    }
}
