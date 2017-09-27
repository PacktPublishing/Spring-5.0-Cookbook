package org.packt.aop.transaction.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"org.packt.aop.transaction", "org.packt.aop.transaction.core", "org.packt.aop.transaction.annotation"})
public class SpringContextConfig {
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public Map<String,Integer> authStore(){
		return new HashMap<>();
	}
	
	@Bean("template")
    TransactionTemplate transactionTemplate(){
        TransactionTemplate template = new TransactionTemplate();
        template.setTransactionManager(transactionManager);
        return template;
    }
	
	
}
