package org.packt.nosql.mongo.core.service.impl;

import org.packt.nosql.mongo.core.dao.DepartmentRepository;
import org.packt.nosql.mongo.core.dao.EmployeeRepository;
import org.packt.nosql.mongo.core.model.data.Employee;
import org.packt.nosql.mongo.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Flux<Employee> getAllEmps() {
		return employeeRepository.findAll();
	}

	@Override
	public Flux<Employee> getAllEmps(Flux<Long> ids) {
		return employeeRepository.findAllById(ids);
	}

	@Override
	public Mono<Employee> getEmpByid(Long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public void saveEmp(Employee emp) {
		employeeRepository.save(emp);
		
	}

	@Override
	public void saveEmps(Flux<Employee> emps) {
		employeeRepository.saveAll(emps);
		
	}

	@Override
	public Flux<Employee> getEmpsByFname(String fname) {
		return employeeRepository.findAllByFirstname(fname);
	}

	@Override
	public Flux<Employee> getEmpsByLname(String lname) {
		return employeeRepository.findAllByLastname(lname);
	}

}
