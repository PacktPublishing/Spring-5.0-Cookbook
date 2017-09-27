package org.packt.microservice.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumeFeignBootApplication extends  SpringBootServletInitializer  {
		
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConsumeFeignBootApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ConsumeFeignBootApplication.class, args);
    }
}
