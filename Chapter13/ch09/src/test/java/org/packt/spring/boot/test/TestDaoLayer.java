package org.packt.spring.boot.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.spring.boot.HRBootApplication;
import org.packt.spring.boot.config.SpringAsynchConfig;
import org.packt.spring.boot.config.SpringContextConfig;
import org.packt.spring.boot.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes={ HRBootApplication.class, SpringContextConfig.class, SpringAsynchConfig.class })
public class TestDaoLayer {
	
	@Autowired
	private DepartmentDao departmentDaoImpl;
	
	@Test
	public void testDeptDao(){
		assertNotNull(departmentDaoImpl.getDepartments());
		System.out.println(departmentDaoImpl.getDepartments());
	}
}
