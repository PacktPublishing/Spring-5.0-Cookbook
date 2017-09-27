package org.packt.nosql.mongo.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MongoBootApplication extends  SpringBootServletInitializer  {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MongoBootApplication.class);
    }
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MongoBootApplication.class, args);
    }
}