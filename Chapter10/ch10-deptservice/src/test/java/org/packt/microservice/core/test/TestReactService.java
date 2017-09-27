package org.packt.microservice.core.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.microservice.core.HRDeptBootApplication;
import org.packt.microservice.core.config.HttpServerConfig;
import org.packt.microservice.core.config.SpringAsynchConfig;
import org.packt.microservice.core.config.SpringDataConfig;
import org.packt.microservice.core.model.data.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes={ HRDeptBootApplication.class, HttpServerConfig.class, SpringDataConfig.class, SpringAsynchConfig.class })
public class TestReactService {

	@Autowired
	private WebTestClient webTestClient;
	
	 @Test
	    public void testDeptById(){
	    		    	
	    	FluxExchangeResult<Department> result = webTestClient.get().uri("http://localhost:8090/ch10-dept/selectReactDept/359").accept(MediaType.APPLICATION_JSON_UTF8)
	    			.exchange().returnResult(Department.class);
	    	assertEquals( result.getStatus().value(), 200);
			Department dept = result.getResponseBody().blockFirst();
			System.out.println(dept.getName());
			
	    }
	 
	   @Test
	    public void testDeptByIdRouter(){
	    	
	    	
	    	FluxExchangeResult<Department> result = webTestClient.get().uri("http://localhost:8901/selectDeptById/359").accept(MediaType.APPLICATION_JSON_UTF8)
	    			.exchange().returnResult(Department.class);
	    	assertEquals( result.getStatus().value(), 200);
			Department dept = result.getResponseBody().blockFirst();
			System.out.println(dept.getName());
			
	    }
}
