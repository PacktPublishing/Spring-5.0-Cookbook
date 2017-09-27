package org.packt.spring.boot.dao;

import java.util.List;

import org.packt.spring.boot.model.data.Department;

public interface DepartmentDao {
	public List<Department> getDepartments();
	public Department getDepartmentData(Integer id);
	public void addDepartmentBySJI(Department dept);
	public void addDepartmentByJT(Department dept);
	public void updateDepartment(Department dept);
	public void delDepartment(Integer deptId);

}
