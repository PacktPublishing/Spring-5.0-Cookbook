package org.packt.aop.transaction.dispatcher;


import javax.inject.Inject;

import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;


@EnableWebMvc
@ComponentScan(basePackages="org.packt.aop.transaction")
@PropertySource("classpath:config/jdbc.properties")
@Configuration
public class SpringDispatcherConfig extends WebMvcConfigurerAdapter{
	
	@Inject
    private ResourceLoader resourceLoader;
	

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
	
	
	@Bean
	public CacheManager cacheManager(){
		CacheManager cm = new EhCacheCacheManager( ehCacheCacheManager().getObject());
		return cm;
	}
	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(resourceLoader.getResource("classpath:ehcache.xml"));
		
		cmfb.setShared(true);
		return cmfb;
	}
	
	
	
	
}
