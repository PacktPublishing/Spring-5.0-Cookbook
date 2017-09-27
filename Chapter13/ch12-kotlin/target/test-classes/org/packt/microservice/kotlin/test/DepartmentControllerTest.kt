package org.packt.microservice.kotlin.test

import org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DepartmentControllerTest {
	
	 @Autowired
	 lateinit var restTemplate: TestRestTemplate
	 
	 @Test
	 fun testRestData() {
	       	val listDepts = restTemplate.getForObject("/listdepts", List::class.java)
	        assertTrue(listdepts.size > 0)
	  }

}
