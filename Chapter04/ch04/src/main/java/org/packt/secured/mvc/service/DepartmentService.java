package org.packt.secured.mvc.service;

import java.util.List;

import org.packt.secured.mvc.model.data.Department;
import org.packt.secured.mvc.model.form.DepartmentForm;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

public interface DepartmentService {
	
	@Secured("ROLE_USER")
	//@PreAuthorize("hasRole('USER') OR hasRole('READ')")
	public List<Department> readDepartments();
	
	@Secured("ROLE_USER")
	//@PreAuthorize("hasRole('USER') OR hasRole('WRITE')")
	public void addDepartment(DepartmentForm dept);
	
	@Secured("ROLE_ADMIN")
	//@PreAuthorize("hasRole('ADMIN') OR hasRole('DELETE')")
	public void removeDepartment(Integer deptId);
	
	@PreAuthorize("hasRole('USER') AND hasRole('HR')")
	//@PreAuthorize("hasRole('HR') AND hasRole('UPDATE')")
	public void updateDepartment(DepartmentForm dept, Integer id);
	
	@PreAuthorize("hasRole('USER') AND hasRole('HR')")
	//@PreAuthorize("hasRole('USER') AND hasRole('READ')")
	public Department getDeptId(Integer id);

}
