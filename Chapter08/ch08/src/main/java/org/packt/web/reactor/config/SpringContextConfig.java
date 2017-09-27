package org.packt.web.reactor.config;

import org.packt.web.reactor.security.config.AppSecurityConfig;
import org.packt.web.reactor.validator.EmployeeValidator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Import(value = { AppSecurityConfig.class })
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.packt.web.reactor", "org.packt.web.reactor.model"})
public class SpringContextConfig  {
	
	
	@Bean
	public EmployeeValidator employeeValidator(){
		return new EmployeeValidator();
	}
		
	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
	    MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
	    methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
	    methodInvokingFactoryBean.setTargetMethod("setStrategyName");
	    methodInvokingFactoryBean.setArguments(new String[]{SecurityContextHolder.MODE_INHERITABLETHREADLOCAL});
	    return methodInvokingFactoryBean;
	}
}
