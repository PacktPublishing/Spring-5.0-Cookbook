package org.packt.dissect.mvc.context;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.packt.dissect.mvc.model.data")
public class SpringDbConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		
		/*
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		dataSource.setUsername(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		*/
		
		
		/*
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		dataSource.setUsername(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		dataSource.setMaxTotal(100);
		*/
		
		/*
	    ComboPooledDataSource dataSource = new ComboPooledDataSource();
	    dataSource.setDriverClass(environment.getProperty("jdbc.driverClassName"));
	    dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
	    dataSource.setUser(environment.getProperty("jdbc.username"));
	    dataSource.setPassword(environment.getProperty("jdbc.password"));
	    dataSource.setMaxPoolSize(100);
	    */
		
	    /*	
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		PoolProperties props = new PoolProperties();
		props.setUrl(environment.getProperty("jdbc.url"));
	    props.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
	    props.setUsername(environment.getProperty("jdbc.username"));
	    props.setPassword(environment.getProperty("jdbc.password"));
	    props.setMaxActive(100);
	    dataSource.setPoolProperties(props);
	    */
	       
	        
		
		 HikariDataSource dataSource = new HikariDataSource();
		 dataSource.setMaximumPoolSize(100);
		 dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
		 dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		 dataSource.setUsername(environment.getProperty("jdbc.username"));
		 dataSource.setPassword(environment.getProperty("jdbc.password"));
		 dataSource.setMaximumPoolSize(100);
	     
		 return dataSource;
	}

	@Bean
	public SimpleJdbcInsert jdbcInsert() throws PropertyVetoException {
		return new SimpleJdbcInsert(dataSource());
	}
	
	

}
