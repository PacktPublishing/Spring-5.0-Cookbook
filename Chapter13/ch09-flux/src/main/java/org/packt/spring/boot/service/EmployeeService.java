package org.packt.spring.boot.service;

import java.util.List;

import org.packt.spring.boot.model.data.Employee;

public interface EmployeeService {
	
    public List<Employee> findEmployeeByDeptid(Integer deptid);
	public List<Employee> findEmployeeByFirstname(String firstname);
	public List<Employee> findEmployeeByLastname(String lastname);
	public List<Employee> findEmployeeByAge(Integer age);
	public List<Employee> findAllEmps();
}
