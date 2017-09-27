package org.packt.dissect.mvc.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.dissect.mvc.context.SpringDbConfig;
import org.packt.dissect.mvc.dao.DepartmentDao;
import org.packt.dissect.mvc.dispatcher.SpringDispatcherConfig;
import org.packt.dissect.mvc.model.data.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig.class, SpringDispatcherConfig.class })
public class TestDepartmentDao {

	@Autowired
	private DepartmentDao departmentDaoImpl;
	
	@Test
	public void testDetachedDepartment(){
		Department rec = new Department();
		rec.setDeptId(9999);
		rec.setName("Security Department");
		departmentDaoImpl.addDepartmentBySJI(rec);
	}
	
	@Test
	public void testPopulateDepartment(){
		Department rec1 = new Department();
		rec1.setDeptId(1);
		rec1.setName("Engineering Department");
		
		Department rec2 = new Department();
		rec2.setDeptId(2);
		rec2.setName("Human Resources Department");
		
		Department rec3 = new Department();
		rec3.setDeptId(3);
		rec3.setName("Arts Department");
		
		Department rec4 = new Department();
		rec4.setDeptId(4);
		rec4.setName("Communications Department");
		
		Department rec5 = new Department();
		rec5.setDeptId(5);
		rec5.setName("Management Department");
		
		departmentDaoImpl.addDepartmentByJT(rec1);
		departmentDaoImpl.addDepartmentByJT(rec2);
		departmentDaoImpl.addDepartmentByJT(rec3);
		departmentDaoImpl.addDepartmentByJT(rec4);
		departmentDaoImpl.addDepartmentByJT(rec5);
	}
	
	@Test
	public void testGetDepartment(){
		Department dept = departmentDaoImpl.getDepartmentData(8);
		assertNotNull(dept);
	}
	
	@Test
	public void testUpdateDepartment(){
		Department rec1 = new Department();
		rec1.setId(9);
		rec1.setDeptId(555555);
		rec1.setName("Accounting Department");
		departmentDaoImpl.updateDepartment(rec1);
		Department dept = departmentDaoImpl.getDepartmentData(9);
		assertSame("Accounting Department",dept.getName());
		
	}
	
	@Test
	public void testDeleteDepartment(){
		departmentDaoImpl.delDepartment(1);
		List<Department> depts = departmentDaoImpl.getDepartments();
		assertTrue(depts.size() > 0);
	}
	
	@Test
	public void testReadDepartmentRecords(){
		List<Department> depts = departmentDaoImpl.getDepartments();
		assertNotNull(depts);
		for(Department d : depts){
			System.out.println(d.getName());
		}
	}
}
