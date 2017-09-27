package org.packt.microservice.core.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.packt.microservice.core.dao.DepartmentRepository;
import org.packt.microservice.core.model.data.Department;
import org.packt.microservice.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Future<List<Department>> findAllByDeptId(Integer deptId) {
			return departmentRepository.findAllByDeptid(deptId);
	}

	@Override
	public CompletableFuture<Department> findAllFirstByNameIgnoreCase(String name) {
		return departmentRepository.findIgnoreCaseByName(name);
	}

	@Override
	public ListenableFuture<Department> findAllFirstById(Integer id) {
		return departmentRepository.findDeptById(id);
	}

	@Override
	public List<Department> findAllDeptList() {
		return departmentRepository.findAll();
	}

	@Override
	public Department findAllDeptById(Integer id) {
		return departmentRepository.findById(id).orElse(new Department());
	}

	@Override
	public List<Department> findAllByDeptName(String name) {
		return departmentRepository.findByName(name);
	}
}
