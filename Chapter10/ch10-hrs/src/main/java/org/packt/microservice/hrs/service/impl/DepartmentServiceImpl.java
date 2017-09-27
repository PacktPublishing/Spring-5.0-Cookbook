package org.packt.microservice.hrs.service.impl;

import java.util.List;

import org.packt.microservice.hrs.dao.DepartmentRepository;
import org.packt.microservice.hrs.model.data.Department;
import org.packt.microservice.hrs.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
