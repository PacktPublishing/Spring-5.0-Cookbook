package org.packt.hiber.core.controller;

import java.util.List;

import org.packt.hiber.core.model.data.Department;
import org.packt.hiber.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiberController {
	
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	@GetMapping("/test")
	@ResponseBody
	public String test(){
		return "test";
	}
	
	@GetMapping(value="/listDepts", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Department> listDepts() {
		return departmentServiceImpl.getAllDeptList();
	}
	
	@GetMapping(value="/selectDept/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public Department selectDept(@PathVariable("id") Integer id) {
		return departmentServiceImpl.getDeptById(id);
	}
	
	@GetMapping(value="/selectDeptByDeptid/{deptid}", produces= MediaType.APPLICATION_JSON_VALUE)
	public List<Department>selectDeptByDeptid(@PathVariable("deptid") Integer deptid) {
		return departmentServiceImpl.getDeptsByDeptid(deptid);
	}
}
