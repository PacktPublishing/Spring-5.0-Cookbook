package org.packt.microservice.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DeptDockerBootApplication  extends  SpringBootServletInitializer  {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DeptDockerBootApplication.class);
    }
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DeptDockerBootApplication.class, args);
    }
}