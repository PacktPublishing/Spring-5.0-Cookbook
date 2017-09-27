package org.packt.spring.boot.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;


@Configuration
@EnableWebMvc
public class SpringMvcConfig extends WebMvcConfigurerAdapter { 
	
	   @Bean
	    public InternalResourceViewResolver getViewResolver() {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("/WEB-INF/");
	        resolver.setSuffix(".html");
	        resolver.setOrder(3);
	        return resolver;
	    }

	    @Override
	    public void configureDefaultServletHandling(
	            DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }    
	    
	    @Bean
		public ResourceBundleViewResolver bundleViewResolver(){
			ResourceBundleViewResolver viewResolverB = new ResourceBundleViewResolver();
			viewResolverB.setBasename("config.views");
			viewResolverB.setOrder(0);
			return viewResolverB;
		}
	    
	  
		@Bean
		public MessageSource messageSource() {
		    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		    messageSource.setBasenames("classpath:config/messages_en_US", "classpath:config/errors");
		    messageSource.setUseCodeAsDefaultMessage(true);
		    messageSource.setDefaultEncoding("UTF-8");
		    messageSource.setCacheSeconds(1);
		    return messageSource;
		}
	
		@Bean
	    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		  return new PropertySourcesPlaceholderConfigurer();
	    }
		
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry
				.addResourceHandler("/css/**")
				.addResourceLocations("/js/")
				.setCachePeriod(31556926);
		}
}
