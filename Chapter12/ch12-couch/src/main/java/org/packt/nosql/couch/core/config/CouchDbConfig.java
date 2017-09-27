package org.packt.nosql.couch.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

@Configuration
public class CouchDbConfig {
	
	@Bean
	public Database hrsDb(CloudantClient cloudant) {
		return cloudant.database("hrs", true);
	}
	
	
	
}
