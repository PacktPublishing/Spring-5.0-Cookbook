package org.packt.spring.boot.dao;

import java.util.List;

import org.packt.spring.boot.model.data.Department;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel="depts", path="depts")
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
	@Cacheable("departmentCache")
	public List<Department> findByName(@Param("name") String name);
	
	@Cacheable("departmentCache")
	public List<Department> findByDeptid(@Param("deptid")  Integer deptId);
}
