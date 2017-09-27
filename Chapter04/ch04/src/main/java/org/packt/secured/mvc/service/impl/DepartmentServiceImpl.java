package org.packt.secured.mvc.service.impl;

import java.util.List;

import org.packt.secured.mvc.model.data.Department;
import org.packt.secured.mvc.model.form.DepartmentForm;
import org.packt.secured.mvc.dao.DepartmentDao;
import org.packt.secured.mvc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDaoImpl;

	@Override
	public List<Department> readDepartments() {
		// TODO Auto-generated method stub
		return departmentDaoImpl.getDepartments();
	}

	@Override
	public void addDepartment(DepartmentForm dept) {
		
		Department deptData = new Department();
		deptData.setDeptId(dept.getDeptId());
		deptData.setName(dept.getName());
		departmentDaoImpl.addDepartmentByJT(deptData);

	}

	@Override
	public void removeDepartment(Integer deptId) {
		departmentDaoImpl.delDepartment(deptId);
		
	}

	@Override
	public void updateDepartment(DepartmentForm dept, Integer id) {
		Department deptData = new Department();
		deptData.setDeptId(dept.getDeptId());
		deptData.setName(dept.getName());
		deptData.setId(id);
		departmentDaoImpl.updateDepartment(deptData);
		
	}

	@Override
	public Department getDeptId(Integer id) {
		Department dept = departmentDaoImpl.getDepartmentData(id);
		return dept;
	}

}
