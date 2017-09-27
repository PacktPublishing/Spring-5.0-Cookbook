package org.packt.spring.boot.service.impl;

import java.util.List;

import org.packt.spring.boot.dao.DepartmentRepository;
import org.packt.spring.boot.model.data.Department;
import org.packt.spring.boot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Department> getAllDepts() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

}
