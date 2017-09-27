package org.packt.microservice.core.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.packt.microservice.core.dao.EmployeeRepository;
import org.packt.microservice.core.model.data.Employee;
import org.packt.microservice.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
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

	@Override
	public void saveEmployeeRec(Employee emp) {
		employeeRepository.saveAndFlush(emp);
		
	}

	@Override
	public Employee findEmployeeByid(Integer id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).get();
	}

	@Override
	public CompletableFuture<List<Employee>> readEmployees() {
	
		return CompletableFuture.completedFuture(employeeRepository.findAll());
	}
	
	@Async
	public Future<Employee> readEmployee(Integer id) {
		
		return new AsyncResult<>(employeeRepository.findById(id).orElse(new Employee()));
	}
}
