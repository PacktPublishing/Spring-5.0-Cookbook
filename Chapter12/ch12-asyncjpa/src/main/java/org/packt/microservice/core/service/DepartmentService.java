package org.packt.microservice.core.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.packt.microservice.core.model.data.Department;
import org.springframework.util.concurrent.ListenableFuture;

public interface DepartmentService {
	
   public List<Department> findAllDeptList();
   public Department findAllDeptById(Integer id);
   public List<Department> findAllByDeptName(String name);
    
   public Future<List<Department>> findAllByDeptId(Integer deptId);
   public CompletableFuture<Department> findAllFirstByNameIgnoreCase(String name);
   public ListenableFuture<Department> findAllFirstById(Integer id);  
}
