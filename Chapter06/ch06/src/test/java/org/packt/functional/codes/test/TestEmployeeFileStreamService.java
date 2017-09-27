package org.packt.functional.codes.test;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.functional.codes.config.SpringDbConfig;
import org.packt.functional.codes.dispatcher.SpringDispatcherConfig;
import org.packt.functional.codes.service.impl.EmployeeFileStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig.class, SpringDispatcherConfig.class })
public class TestEmployeeFileStreamService {
	
	@Autowired
	private EmployeeFileStreamService employeeFileStreamService;
	
	@Test
	public void testFileRetrieve(){
		try {
			employeeFileStreamService.viewFileContent("./src/test/java/org/packt/functional/codes/test/employee_list.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCountFileLines(){
		try {
			long numWords= employeeFileStreamService.countRecsInFile("./src/test/java/org/packt/functional/codes/test/employee_list.txt");
			System.out.println(numWords);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testWalkDirTree(){
		try {
			employeeFileStreamService.viewDirFiles("./src/main/java/org/packt/functional/codes/service/impl", ".java");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCountWordsByAge(){
		try {
			long numWords= employeeFileStreamService.countRecByAge("./src/test/java/org/packt/functional/codes/test/employee_list.txt", 20);
			System.out.println(numWords);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
