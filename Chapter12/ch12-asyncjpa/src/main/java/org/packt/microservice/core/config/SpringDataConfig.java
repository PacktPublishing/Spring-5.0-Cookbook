package org.packt.microservice.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages="org.packt.microservice.core.dao")
@EnableTransactionManagement
public class SpringDataConfig { }
