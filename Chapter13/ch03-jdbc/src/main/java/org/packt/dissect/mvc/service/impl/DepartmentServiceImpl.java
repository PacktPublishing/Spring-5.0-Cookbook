package org.packt.dissect.mvc.service.impl;

import java.util.List;

import org.packt.dissect.mvc.dao.DepartmentDao;
import org.packt.dissect.mvc.model.data.Department;
import org.packt.dissect.mvc.model.form.DepartmentForm;
import org.packt.dissect.mvc.service.DepartmentService;
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
		departmentDaoImpl.addDepartmentBySJI(deptData);

	}

}
