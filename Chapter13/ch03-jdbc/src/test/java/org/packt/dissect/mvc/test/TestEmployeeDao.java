package org.packt.dissect.mvc.test;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.dissect.mvc.context.SpringDbConfig;
import org.packt.dissect.mvc.dao.EmployeeDao;
import org.packt.dissect.mvc.dispatcher.SpringDispatcherConfig;
import org.packt.dissect.mvc.model.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig.class, SpringDispatcherConfig.class })
public class TestEmployeeDao {

	@Autowired
	private EmployeeDao employeeDaoImpl;
	
	@Test
	public void testPersistEmployee(){
		Employee rec = new Employee();
		rec.setEmpId(676767);
		rec.setFirstName("Sherwin");
		rec.setLastName("Tragura");
		rec.setAge(38);
		rec.setBirthday(new Date(78, 10, 30));
		rec.setEmail("sjctrags@gmail.com");
		rec.setEmpId(1065);
		rec.setDeptId(9);
		employeeDaoImpl.addEmployeeBySJI(rec);
	}

	@Test
	public void testPopulateEmployees() {
		Employee rec1 = new Employee();
		rec1.setEmpId(12345);
		rec1.setFirstName("Anna");
		rec1.setLastName("Lasi");
		rec1.setAge(40);
		rec1.setBirthday(new Date(78, 10, 10));
		rec1.setEmail("anna@gmail.com");
		rec1.setEmpId(8780);
		rec1.setDeptId(9);
		
		Employee rec2 = new Employee();
		rec2.setEmpId(67893);
		rec2.setFirstName("Joan");
		rec2.setLastName("Arko");
		rec2.setAge(40);
		rec2.setBirthday(new Date(77, 03, 05));
		rec2.setEmail("joan@yahoo.com");
		rec2.setEmpId(1111);
		rec2.setDeptId(10);
		
		Employee rec3 = new Employee();
		rec3.setEmpId(67658);
		rec3.setFirstName("Louise");
		rec3.setLastName("Kellogs");
		rec3.setAge(40);
		rec3.setBirthday(new Date(88, 06, 22));
		rec3.setEmail("kellogs_me@aol.com");
		rec3.setEmpId(7878);
		rec3.setDeptId(11);
		
		employeeDaoImpl.addEmployeeByJT(rec1);
		employeeDaoImpl.addEmployeeByJT(rec2);
		employeeDaoImpl.addEmployeeByJT(rec3);
	}

	@Test
	public void testSelectDepts() {
		List<Employee> employees = employeeDaoImpl.getEmployees();
		for(Employee emp: employees){
			System.out.println(emp.getFirstName());
		}
	}
	
	@Test
	public void testUpdateEmployee(){
		Employee rec1 = new Employee();
		rec1.setId(7);
		rec1.setFirstName("Maria");
		rec1.setLastName("Lasi");
		rec1.setAge(45);
		rec1.setBirthday(new Date(78, 10, 10));
		rec1.setEmail("maria@aol.com");
		rec1.setEmpId(8780);
		rec1.setDeptId(13);
			
		employeeDaoImpl.updateEmployee(rec1);
	}
	
	@Test
	public void testDeleteEmployee(){
		employeeDaoImpl.delEmployee(8780);
	}

}
