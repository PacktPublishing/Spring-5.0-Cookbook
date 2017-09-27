package org.packt.nosql.mongo.core.service;

import org.packt.nosql.mongo.core.model.data.Department;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentService {
	
	public Flux<Department> getAllDepts();
	public Flux<Department> getAllDepts(Flux<Long> ids);
	
	public Mono<Department> getDeptByid(Long id);
	
	public void saveDept(Department dept);
	public void saveDepts(Flux<Department> depts);

}
