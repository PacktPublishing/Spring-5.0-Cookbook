package org.packt.nosql.mongo.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages="org.packt.nosql.mongo.core.dao")
@EnableTransactionManagement
public class ReactiveDataConfig { }
