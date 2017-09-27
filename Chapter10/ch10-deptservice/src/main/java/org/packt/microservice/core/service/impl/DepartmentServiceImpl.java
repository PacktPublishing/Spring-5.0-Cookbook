package org.packt.microservice.core.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.packt.microservice.core.dao.DepartmentRepository;
import org.packt.microservice.core.model.data.Department;
import org.packt.microservice.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Department> findAllDepts() {
		return departmentRepository.findAll();
	}

	@Override
	public List<Department> findDeptsByName(String name) {
		return departmentRepository.findByName(name);
	}

	@Override
	public List<Department> findDeptsByDeptId(Integer deptid) {
		return departmentRepository.findByDeptid(deptid);
	}

	@Override
	public void saveDeptRec(Department dept) {
		departmentRepository.save(dept);
		
	}

	@Override
	public Department findDeptByid(Integer id) {
		// TODO Auto-generated method stub
		return departmentRepository.findById(id).get();
	}
	
	@Override
	public CompletableFuture<List<Department>> readDepartments() {
	
		return CompletableFuture.completedFuture(departmentRepository.findAll());
	}
	
	@Async
	public Future<Department> readDepartment(Integer id) {
		
		return new AsyncResult<>(departmentRepository.findById(id).orElse(new Department()));
	}
}
