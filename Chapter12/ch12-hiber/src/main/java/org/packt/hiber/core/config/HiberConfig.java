package org.packt.hiber.core.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class HiberConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.show_sql"));
        return properties;        
    }
	
	@Bean("sessionFactory")
	public LocalSessionFactoryBean localSessionFactory(DataSource dataSource, Properties hibernateProperties) {
	    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	    sessionFactory.setDataSource(dataSource);
	    sessionFactory.setPackagesToScan(new String[] { "org.packt.hiber.core.model.data" });
	    sessionFactory.setHibernateProperties(hibernateProperties);
	    return sessionFactory;
	}
	
	@Bean
	public HibernateTransactionManager db1TransactionManager(DataSource dataSource,LocalSessionFactoryBean localSessionFactory) {
	       HibernateTransactionManager txManager = new HibernateTransactionManager();
	       txManager.setSessionFactory(localSessionFactory.getObject());
	       txManager.setDataSource(dataSource);
	       return txManager;
	}
	
	@Bean
	public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
	        return new HibernateTemplate(sessionFactory);
	}
}
