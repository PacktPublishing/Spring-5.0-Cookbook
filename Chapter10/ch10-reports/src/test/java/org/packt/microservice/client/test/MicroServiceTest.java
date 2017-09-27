package org.packt.microservice.client.test;


import java.sql.Date;
import java.time.Duration;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.microservice.client.HRClientBootApplication;
import org.packt.microservice.client.config.WebFluxConfig;
import org.packt.microservice.client.model.data.CountDept;
import org.packt.microservice.client.model.data.Department;
import org.packt.microservice.client.model.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.BodyContentSpec;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {WebFluxConfig.class, HRClientBootApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MicroServiceTest {
	
	@Autowired
	private WebTestClient webTestClient;
 
   
    @Test
    public void testEmpList(){
    	
    	
    	FluxExchangeResult<Employee> result = webTestClient.get().uri("http://localhost:9002/routeEmps").accept(MediaType.APPLICATION_JSON)
    			.exchange().returnResult(Employee.class);
    	assert result.getStatus().value() == 200;
		List<Employee> users = result.getResponseBody().collectList().block();
		System.out.println(users.size());
		
    	
    	System.out.println("sherwin");
    }
    
    
    @Test
    public void testEmpById(){
    	
    	
    	FluxExchangeResult<Employee> result = webTestClient.get().uri("http://localhost:9002/selectEmpById/11").accept(MediaType.APPLICATION_JSON_UTF8)
    			.exchange().returnResult(Employee.class);
    	assert result.getStatus().value() == 200;
		Employee user = result.getResponseBody().blockFirst();
		System.out.println(user.getFirstname());
		
    	
    	System.out.println("sherwin");
    }
    
    
    @Test
    public void testEmpByFluxIds(){
    	FluxExchangeResult<Employee> result = webTestClient.post().uri("http://localhost:9002/selectFluxEmps").accept(MediaType.APPLICATION_JSON_UTF8)
    			.body(Flux.just(15, 16, 21), Integer.class).exchange().returnResult(Employee.class);
    	assert result.getStatus().value() == 200;
		List<Employee> users = result.getResponseBody().collectList().block();
		System.out.println(users.get(0).getLastname());
		
    	
    	System.out.println("sherwin");
    	
    }
    
    
    @Test
    public void testSaveEmps(){
    	Employee newEmp = new Employee();
    	newEmp.setEmpid(87978);
    	newEmp.setAge(22);
    	newEmp.setBirthday(new Date(72, 1,22));
    	newEmp.setDeptid(362);
    	newEmp.setEmail("keith@gmail.com");
    	newEmp.setFirstname("Keith");
    	newEmp.setLastname("Smith");
    	
    
    	/*
    	Mono<ClientResponse> resp = WebClient.create().post().uri("http://localhost:8098/saveEmp").accept(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromObject(newEmp)).exchange();
    	*/
       	ResponseSpec resp = webTestClient.post().uri("http://localhost:9002/saveEmp").accept(MediaType.APPLICATION_JSON_UTF8)
    			.body(BodyInserters.fromObject(newEmp)).exchange();
        
		
		
    	System.out.println(resp);
    	System.out.println("sherwin");
    	
    }
    
    @Test
    public void countEmps(){
    	
    	FluxExchangeResult<CountDept> result = webTestClient.get().uri("http://localhost:9001/countDepts").accept(MediaType.APPLICATION_JSON_UTF8)
    			.exchange().returnResult(CountDept.class);
    	assert result.getStatus().value() == 200;
		
		
    	CountDept users = result.getResponseBody().blockFirst();
		System.out.println(users.getCount());
    	
    	System.out.println("sherwin");
    	
    }
    		
    		
// 
}
