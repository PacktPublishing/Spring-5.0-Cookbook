package org.packt.microservice.hrs.service;

import java.util.List;

import org.packt.microservice.hrs.model.data.Employee;

public interface EmployeeService {
	
	public Employee findEmployeeByid(Integer id);
	public List<Employee> findEmployeeByDeptid(Integer deptid);
	public List<Employee> findEmployeeByFirstname(String firstname);
	public List<Employee> findEmployeeByLastname(String lastname);
	public List<Employee> findEmployeeByAge(Integer age);
	public List<Employee> findAllEmps();
	
	
	public void saveEmployeeRec(Employee emp);
}
