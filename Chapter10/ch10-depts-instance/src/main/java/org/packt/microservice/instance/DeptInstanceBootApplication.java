package org.packt.microservice.instance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeptInstanceBootApplication {
		
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DeptInstanceBootApplication.class, args);
    }
}
