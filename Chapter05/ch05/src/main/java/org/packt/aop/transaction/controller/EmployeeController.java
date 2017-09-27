package org.packt.aop.transaction.controller;

import org.packt.aop.transaction.annotation.MonitorRequest;
import org.packt.aop.transaction.annotation.MonitorService;
import org.packt.aop.transaction.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	@MonitorRequest
	@RequestMapping("/deldept.html/{deptId}")
	public String deleteRecord(Model model, @PathVariable("deptId") Integer deptId){
		
		employeeServiceImpl.delEmployee(deptId);
		model.addAttribute("emps", employeeServiceImpl.readEmployees());
		return "menu";
	}
}
