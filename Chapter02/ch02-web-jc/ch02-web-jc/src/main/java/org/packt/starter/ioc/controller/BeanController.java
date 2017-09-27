package org.packt.starter.ioc.controller;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.packt.starter.ioc.model.DataService;
import org.packt.starter.ioc.model.Department;
import org.packt.starter.ioc.model.Employee;
import org.packt.starter.ioc.model.ListEmployees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BeanController {
	
	@Autowired
	@Qualifier(value="empRec2")
	private Employee empRecs;
	
	
	@Inject
	private Department dept2;
	
	@Resource(name="listEmployees")
	private ListEmployees listEmps;
	
	@Autowired
	private DataService dataService;
	
	@RequestMapping("/list_emps.html")
	public String showEmployee(ModelMap model){
		model.addAttribute("firstName", empRecs.getFirstName());
		model.addAttribute("title", dataService.getTitle());
		return "list-employees";
	}
	
	@RequestMapping("/show_dept.html")
	public String showDepartment(Model model){
		model.addAttribute("deptNo", dept2.getDeptNo());
		return "show-dept";
	}
	
	@RequestMapping("/view_emps.html")
	public String viewEmps(Model model){
		model.addAttribute("empList", listEmps.getListEmps());
		return "view-emps";
	}
	
	

}
