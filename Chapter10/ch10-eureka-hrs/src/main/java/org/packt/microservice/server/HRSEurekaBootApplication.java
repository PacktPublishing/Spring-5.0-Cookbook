package org.packt.microservice.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HRSEurekaBootApplication {
	
	public static void main(String[] args) {
	    SpringApplication.run(HRSEurekaBootApplication.class, args);
	}
	
	
}
