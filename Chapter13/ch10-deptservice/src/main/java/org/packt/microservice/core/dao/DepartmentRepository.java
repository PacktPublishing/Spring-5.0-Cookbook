package org.packt.microservice.core.dao;

import java.util.List;

import org.packt.microservice.core.model.data.Department;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
	@Cacheable("departmentCache")
	public List<Department> findByName(String name);
	
	@Cacheable("departmentCache")
	public List<Department> findByDeptid(Integer deptId);

}
