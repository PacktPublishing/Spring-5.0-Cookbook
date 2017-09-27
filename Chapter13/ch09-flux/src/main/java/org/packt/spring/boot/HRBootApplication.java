package org.packt.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HRBootApplication extends  SpringBootServletInitializer  {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HRBootApplication.class);
    }
    
  
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(HRBootApplication.class, args);
    }
}
