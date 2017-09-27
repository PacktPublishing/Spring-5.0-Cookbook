package org.packt.microservice.kotlin.dao

import org.springframework.data.repository.CrudRepository
import org.packt.microservice.kotlin.model.data.Department

interface DepartmentRepository : CrudRepository<Department, Integer>{
	
	fun findByName(name: String): Iterable<Department>
	fun findByDeptid(deptid: Integer): Iterable<Department>
	
}