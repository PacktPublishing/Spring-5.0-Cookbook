package org.packt.microservice.instance.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.packt.microservice.instance.model.data.Department;

public interface DepartmentService {
	
   public Department findDeptByid(Integer id);
   public List<Department> findAllDepts();
   public List<Department> findDeptsByName(String name);
   public List<Department> findDeptsByDeptId(Integer deptid);
   public void saveDeptRec(Department dept);
   
   public CompletableFuture<List<Department>> readDepartments();
   public Future<Department>  readDepartment(Integer id);
}
