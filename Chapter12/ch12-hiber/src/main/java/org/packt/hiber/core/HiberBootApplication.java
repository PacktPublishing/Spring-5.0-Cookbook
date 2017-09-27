package org.packt.hiber.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
@EnableTransactionManagement
public class HiberBootApplication extends SpringBootServletInitializer  {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HiberBootApplication.class);
    }
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(HiberBootApplication.class, args);
    }
}