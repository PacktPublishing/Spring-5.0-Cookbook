package org.packt.dissect.mvc.service;

import java.util.List;

import org.packt.dissect.mvc.model.data.Employee;
import org.packt.dissect.mvc.model.form.EmployeeForm;

public interface EmployeeService {
	
	public List<Employee> readEmployees();
	public void addEmployeee(EmployeeForm emp);
}
