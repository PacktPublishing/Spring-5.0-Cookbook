package org.packt.dissect.mvc.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.packt.dissect.mvc", "org.packt.dissect.mvc.model"})
public class SpringContextConfig {
	
	
}
