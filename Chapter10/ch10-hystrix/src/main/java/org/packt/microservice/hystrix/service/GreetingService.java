package org.packt.microservice.hystrix.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class GreetingService {
    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public String getGreeting() {
        return new RestTemplate()
          .getForObject("http://localhost:8090/ch10-dept/objectSampleRest", 
          String.class);
    }
 
    private String defaultGreeting() {
        return "Hello User!";
    }
}