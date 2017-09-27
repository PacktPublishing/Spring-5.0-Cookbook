package org.packt.microservice.instance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmpsInstanceBootApplication {
	
	 public static void main(String[] args) throws Exception {
	        SpringApplication.run(EmpsInstanceBootApplication.class, args);
	    }

}
