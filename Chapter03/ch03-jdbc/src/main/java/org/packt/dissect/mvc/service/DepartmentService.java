package org.packt.dissect.mvc.service;

import java.util.List;

import org.packt.dissect.mvc.model.data.Department;
import org.packt.dissect.mvc.model.form.DepartmentForm;

public interface DepartmentService {
	
	public List<Department> readDepartments();
	public void addDepartment(DepartmentForm dept);

}
