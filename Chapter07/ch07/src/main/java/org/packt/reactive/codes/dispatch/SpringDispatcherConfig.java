package org.packt.reactive.codes.dispatch;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;


@EnableWebMvc
@ComponentScan(basePackages="org.packt.reactive.codes")
@PropertySource("classpath:config/jdbc.properties")
@Configuration
public class SpringDispatcherConfig extends WebMvcConfigurerAdapter{
	

	@Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolverA = new InternalResourceViewResolver();
        viewResolverA.setPrefix("/WEB-INF/jsp/");
        viewResolverA.setSuffix(".jsp");
        viewResolverA.setOrder(1);
        return viewResolverA;
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
	    messageSource.setBasename("classpath:config/messages_en_US");
	    messageSource.setUseCodeAsDefaultMessage(true);
	    messageSource.setDefaultEncoding("UTF-8");
	    messageSource.setCacheSeconds(1);
	    return messageSource;
	}

	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
	  return new PropertySourcesPlaceholderConfigurer();
    }
	
	
	
	
	
}
