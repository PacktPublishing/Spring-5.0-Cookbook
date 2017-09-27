package org.packt.spring.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages="org.packt.spring.boot.dao")
@Import(RepositoryRestMvcConfiguration.class) // required for Spring REST
@EnableTransactionManagement
public class SpringDataConfig { }
