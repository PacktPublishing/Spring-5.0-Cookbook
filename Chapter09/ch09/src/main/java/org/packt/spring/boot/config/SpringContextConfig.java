package org.packt.spring.boot.config;

import org.packt.spring.boot.security.AppSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Import(value = { AppSecurityConfig.class })
@Configuration
@EnableWebMvc
public class SpringContextConfig  {
			
   @Autowired
   private ApplicationContext applicationContext;
	
   @Bean(name = "viewResolverFTL")
   public FreeMarkerViewResolver getViewResolverFtl() {
       FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
       viewResolver.setPrefix("");
       viewResolver.setSuffix(".ftl");
       viewResolver.setOrder(1);
       return viewResolver;
   }
 
   @Bean(name = "freemarkerConfig")
   public FreeMarkerConfigurer getFreemarkerConfig() {
       FreeMarkerConfigurer config = new FreeMarkerConfigurer();
      config.setTemplateLoaderPath("/WEB-INF/templates/");
       return config;
   }
      
   @Bean(name ="templateResolver")	
   public SpringResourceTemplateResolver getTemplateResolver() {
		 SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		 templateResolver.setApplicationContext(applicationContext);
   	templateResolver.setPrefix("/WEB-INF/templates/");
   	templateResolver.setSuffix(".html");
   	templateResolver.setTemplateMode("XHTML");
   	return templateResolver;
   }
	
   @Bean(name ="templateEngine")	    
   public SpringTemplateEngine getTemplateEngine() {
   	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
   	templateEngine.setTemplateResolver(getTemplateResolver());
   	return templateEngine;
   }
   
   @Bean(name="viewResolverThymeLeaf")
   public ThymeleafViewResolver getViewResolverThyme(){
   	ThymeleafViewResolver viewResolver = new ThymeleafViewResolver(); 
   	viewResolver.setTemplateEngine(getTemplateEngine());
   	viewResolver.setOrder(2);
   	return viewResolver;
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
