package org.packt.dissect.mvc.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.dissect.mvc.context.SpringDbConfig;
import org.packt.dissect.mvc.dispatcher.SpringDispatcherConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer.Context;
import com.codahale.metrics.Timer;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringDbConfig.class, SpringDispatcherConfig.class})
public class TestDbPool {
	
	@Autowired
	private DataSource dataSource;
	
	private static final int MAX_ITERATIONS = 1000;
	private ConsoleReporter logReporter;
	private Timer timer;
	
	@Before
	public void init() {
	    MetricRegistry metricRegistry = new MetricRegistry();
	    
	    this.logReporter = ConsoleReporter
	            .forRegistry(metricRegistry)
	            .build();
	    logReporter.start(1, TimeUnit.MINUTES); 
	    timer = metricRegistry.timer("connection");
	}
	 
	@Test
	public void testOpenCloseConnections() throws SQLException {
	    for (int i = 0; i < MAX_ITERATIONS; i++) {
	        Context context = timer.time();
	        Connection conn = dataSource.getConnection();
	        Statement stmt = conn.createStatement();
	        stmt.executeQuery("select * from city");
	        conn.close();
	        context.stop();
	    }
	    logReporter.report();
	   
	   	}
}
