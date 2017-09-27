package org.packt.microservice.hrs.service;

import java.util.List;

import org.packt.microservice.hrs.model.data.Department;

public interface DepartmentService {
	
   public Department findDeptByid(Integer id);
   public List<Department> findAllDepts();
   public List<Department> findDeptsByName(String name);
   public List<Department> findDeptsByDeptId(Integer deptid);
   
   public void saveDeptRec(Department dept);
   
   
}
