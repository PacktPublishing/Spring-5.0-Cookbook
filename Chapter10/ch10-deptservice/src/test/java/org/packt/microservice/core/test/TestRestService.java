package org.packt.microservice.core.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.microservice.core.HRDeptBootApplication;
import org.packt.microservice.core.config.SpringAsynchConfig;
import org.packt.microservice.core.config.SpringDataConfig;
import org.packt.microservice.core.model.data.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes={ HRDeptBootApplication.class, CacheConfig.class, SpringDataConfig.class, SpringAsynchConfig.class })
public class TestRestService {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void exampleTest() {
		String body = this.restTemplate.getForObject("/objectSampleRest", String.class);
		assertThat(body).isEqualTo("Hello World");
	}
	
	@Test
	public void exampleTestList() {
		String body = this.restTemplate.getForObject("/objectSampleRest", String.class);
		assertThat(body).isEqualTo("Hello World");
	}
	
	@Test
	public void exampleTestListAll() {
		List<Department> body = this.restTemplate.getForObject("/listDept", List.class);
		assertNotNull(body);
		System.out.println(body);
	}

}
