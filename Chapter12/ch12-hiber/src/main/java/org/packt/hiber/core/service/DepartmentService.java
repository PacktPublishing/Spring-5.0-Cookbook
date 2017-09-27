package org.packt.hiber.core.service;

import java.util.List;

import org.packt.hiber.core.model.data.Department;

public interface DepartmentService {

	public List<Department> getAllDeptList();
	public List<Department> getDeptsByName(String name);
	public List<Department> getDeptsByDeptid(Integer deptid);
	public Department getDeptById(Integer id);
	public void saveDept(Department dept);
}
