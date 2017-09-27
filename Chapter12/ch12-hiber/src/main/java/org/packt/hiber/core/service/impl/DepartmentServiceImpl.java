package org.packt.hiber.core.service.impl;

import java.util.List;

import org.packt.hiber.core.dao.DepartmentDao;
import org.packt.hiber.core.model.data.Department;
import org.packt.hiber.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentDao departmentDaoImpl;

	@Override
	public List<Department> getAllDeptList() {
		return departmentDaoImpl.getAllDepts();
	}

	@Override
	public List<Department> getDeptsByName(String name) {
		return departmentDaoImpl.getDeptsByName(name);
	}

	@Override
	public List<Department> getDeptsByDeptid(Integer deptid) {
		return departmentDaoImpl.getDeptsByDeptid(deptid);
	}

	@Override
	public Department getDeptById(Integer id) {
		return departmentDaoImpl.getDeptById(id);
	}

	@Override
	public void saveDept(Department dept) {
		departmentDaoImpl.saveDept(dept);
		
	}

}
