package org.packt.secured.mvc.service;

import java.util.List;

import org.packt.secured.mvc.model.data.Employee;
import org.packt.secured.mvc.model.form.EmployeeForm;

public interface EmployeeService {
	
	public List<Employee> readEmployees();
	public void addEmployeee(EmployeeForm emp);
}
