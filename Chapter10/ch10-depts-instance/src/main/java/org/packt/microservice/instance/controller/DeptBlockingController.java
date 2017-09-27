package org.packt.microservice.instance.controller;


import java.util.List;

import org.packt.microservice.instance.model.data.Department;
import org.packt.microservice.instance.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptBlockingController {
	
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	@GetMapping("/objectSampleRest")
	public String blockString() {
		return "Hello World";
	}
	
	@GetMapping(value="/selectDept/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public Department blockDepartment(@PathVariable("id") Integer id) {
		return departmentServiceImpl.findDeptByid(id);
	}
		
	@GetMapping(value="/listDept", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Department> blockListDept() {
		return departmentServiceImpl.findAllDepts();
	}
	
	
	@PostMapping(value="/saveDeptRec", consumes= MediaType.APPLICATION_JSON_VALUE)
	public Boolean blockSaveDept(@RequestBody Department dept) {
		try{
			departmentServiceImpl.saveDeptRec(dept);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
