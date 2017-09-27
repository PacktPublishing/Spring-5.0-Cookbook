package org.packt.microservice.core.controller;

import java.util.List;

import org.packt.microservice.core.model.data.Employee;
import org.packt.microservice.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpBlockingController {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	
	
	@GetMapping("/objectSampleRest")
	public String blockString() {
		return "Hello World";
	}
	
	@GetMapping(value="/selectEmp/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public Employee blockEmployee(@PathVariable("id") Integer id) {
		return employeeServiceImpl.findEmployeeByid(id);
	}
		
	@GetMapping(value="/listEmp", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> blockListEmp() {
		return employeeServiceImpl.findAllEmps();
	}
	
	
	@PostMapping(value="/saveEmpRec", consumes= MediaType.APPLICATION_JSON_VALUE)
	public Boolean blockSaveDept(@RequestBody Employee emp) {
		try{
			employeeServiceImpl.saveEmployeeRec(emp);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
}
