package org.packt.microservice.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.packt.microservice.client")
@EnableAutoConfiguration(exclude={FreeMarkerAutoConfiguration.class, ThymeleafAutoConfiguration.class})
public class HRClientBootApplication extends  SpringBootServletInitializer  {
	//public class HRBootApplication {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HRClientBootApplication.class);
    }
    
  
    public static void main(String[] args) throws Exception {
        SpringApplication.run(HRClientBootApplication.class, args);
    }

}
