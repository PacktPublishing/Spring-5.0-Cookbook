package org.packt.nosql.mongo.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration		
@EnableReactiveMongoRepositories(basePackages="org.packt.nosql.mongo.core.dao")
@EnableWebFlux
public class MongoConfig extends AbstractReactiveMongoConfiguration {

	@Override
	public MongoClient mongoClient() {
		return MongoClients.create();
	}

	@Override
	protected String getDatabaseName() {
		return "hrs";
	}
	
	@Bean		 
	public ReactiveMongoTemplate reactiveMongoTemplate() {		    
		return new ReactiveMongoTemplate(mongoClient(), getDatabaseName());		 
	}
}
