package org.packt.microservice.pms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class PMSBootApplication {
	
	public static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNTS-MICROSERVICE";
	 
	 public static void main(String[] args) {
	  SpringApplication.run(PMSBootApplication.class, args);
	 }
	 
	 @Bean
	 @LoadBalanced
	 public RestTemplate restTemplate() {
	  return new RestTemplate();
	 }
	 
	 /*
	 @Bean
	 public AccountRepository accountRepository(){
	  return new RemoteAccountRepository(ACCOUNTS_SERVICE_URL);
	 }
	 
	 */

}
