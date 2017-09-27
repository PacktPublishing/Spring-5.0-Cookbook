package org.packt.spring.boot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication
@Configuration
@ComponentScan("org.packt.spring.boot")
@EnableAutoConfiguration(exclude={FreeMarkerAutoConfiguration.class, ThymeleafAutoConfiguration.class})
public class HRBootApplication extends  SpringBootServletInitializer  {
	
	private static Logger logger = LoggerFactory.getLogger(HRBootApplication.class);
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("HR applications starting...");
        return application.sources(HRBootApplication.class);
    }

    public static void main(String[] args) throws Exception {
    	logger.info("HR applications starting @ command line...");
        SpringApplication.run(HRBootApplication.class, args);
    }
}
