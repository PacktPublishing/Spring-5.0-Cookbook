package org.packt.spring.boot.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

	@Bean
	@Qualifier("caffeineCacheManager")
	public CaffeineCacheManager caffeineCacheManager() {
	    return new CaffeineCacheManager();
	}
	
}
