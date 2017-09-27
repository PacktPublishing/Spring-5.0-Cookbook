package org.packt.spring.boot.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.spring.boot.HRBootApplication;
import org.packt.spring.boot.config.CachingConfig;
import org.packt.spring.boot.config.SpringDataConfig;
import org.packt.spring.boot.config.WebFluxConfig;
import org.packt.spring.boot.dao.DepartmentRepository;
import org.packt.spring.boot.dao.EmployeeRepository;
import org.packt.spring.boot.model.data.Department;
import org.packt.spring.boot.model.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes={ HRBootApplication.class, CachingConfig.class, SpringDataConfig.class, WebFluxConfig.class})
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class TestEmployeeRepository {
		
	@Autowired
    private TestEntityManager entityManager;
	 
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	public void testLoadGames() {
		
		List<Employee> deptTest =  employeeRepository.findAll();
		System.out.println(deptTest.size());

	}
}
