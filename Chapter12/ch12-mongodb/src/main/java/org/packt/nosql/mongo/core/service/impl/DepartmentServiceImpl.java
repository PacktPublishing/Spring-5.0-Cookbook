package org.packt.nosql.mongo.core.service.impl;

import org.packt.nosql.mongo.core.dao.DepartmentRepository;
import org.packt.nosql.mongo.core.model.data.Department;
import org.packt.nosql.mongo.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Flux<Department> getAllDepts() {
			return departmentRepository.findAll();
	}

	@Override
	public Flux<Department> getAllDepts(Flux<Long> ids) {
		return departmentRepository.findAllById(ids);
	}

	@Override
	public Mono<Department> getDeptByid(Long id) {
		return departmentRepository.findById(id);
	}

	@Override
	public void saveDept(Department dept) {
		departmentRepository.save(dept);

	}

	@Override
	public void saveDepts(Flux<Department> depts) {
		departmentRepository.saveAll(depts);

	}

}
