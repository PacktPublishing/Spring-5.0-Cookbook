package org.packt.secured.mvc.context;

import org.packt.secured.mvc.core.AppSecurityConfig;
import org.packt.secured.mvc.core.AppSecurityModelA;
import org.packt.secured.mvc.core.AppSecurityModelB;
import org.packt.secured.mvc.core.AppSecurityModelC;
import org.packt.secured.mvc.core.AppSecurityModelD;
import org.packt.secured.mvc.core.AppSecurityModelE;
import org.packt.secured.mvc.core.AppSecurityModelE2;
import org.packt.secured.mvc.core.AppSecurityModelF;
import org.packt.secured.mvc.core.AppSecurityModelG;
import org.packt.secured.mvc.core.AppSecurityModelH;
import org.packt.secured.mvc.core.AppSecurityModelI;
import org.packt.secured.mvc.core.AppSecurityModelJ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Import(value = { AppSecurityModelC.class })
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.packt.secured.mvc", "org.packt.secured.mvc.core.manager", "org.packt.secured.mvc.core.handler", "org.packt.secured.mvc.core.service"})
public class SpringContextConfig {
	
	  @Bean
	  public HttpSessionEventPublisher httpSessionEventPublisher() {
			    return new HttpSessionEventPublisher();
	 }
}
