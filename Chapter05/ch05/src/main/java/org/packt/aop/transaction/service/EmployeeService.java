package org.packt.aop.transaction.service;

import java.util.List;

import org.packt.aop.transaction.model.data.Employee;
import org.packt.aop.transaction.model.form.EmployeeForm;

public interface EmployeeService {
	
	public List<Employee> readEmployees();
	public Employee readEmployee(Integer empId);
	public void addEmployee(EmployeeForm emp);
	
	
	public void updateEmployee(EmployeeForm emp, int id) ;
	public void delEmployee(Integer empId);
}
