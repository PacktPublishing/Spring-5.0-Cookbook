package org.packt.hiber.core.dao;

import java.util.List;

import org.packt.hiber.core.model.data.Department;

public interface DepartmentDao {

	public List<Department> getAllDepts();
	public List<Department> getDeptsByName(String name);
	public List<Department> getDeptsByDeptid(Integer deptid);
	public Department getDeptById(Integer id);
	public void saveDept(Department dept);
}
