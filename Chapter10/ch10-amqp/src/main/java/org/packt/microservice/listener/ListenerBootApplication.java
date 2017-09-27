package org.packt.microservice.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ListenerBootApplication extends  SpringBootServletInitializer  {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ListenerBootApplication.class);
    }
    
  
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ListenerBootApplication.class, args);
    }

}
