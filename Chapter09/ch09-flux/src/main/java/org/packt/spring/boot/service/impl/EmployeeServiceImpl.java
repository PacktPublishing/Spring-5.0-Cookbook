package org.packt.spring.boot.service.impl;

import java.util.List;

import org.packt.spring.boot.dao.EmployeeRepository;
import org.packt.spring.boot.model.data.Employee;
import org.packt.spring.boot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findEmployeeByDeptid(Integer deptid) {
		return employeeRepository.findByDeptid(deptid);
	}

	@Override
	public List<Employee> findEmployeeByFirstname(String firstname) {
		return employeeRepository.findByFirstname(firstname);
	}

	@Override
	public List<Employee> findEmployeeByLastname(String lastname) {
		return employeeRepository.findByLastname(lastname);
	}

	@Override
	public List<Employee> findEmployeeByAge(Integer age) {
		return employeeRepository.findByAge(age);
	}

	@Override
	public List<Employee> findAllEmps() {
		
		return employeeRepository.findAll();
	}
}
