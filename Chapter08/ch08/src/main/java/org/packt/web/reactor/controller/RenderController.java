package org.packt.web.reactor.controller;

import org.packt.web.reactor.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.spring5.context.webflux.ReactiveLazyContextVariable;

@Controller
public class RenderController {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	@RequestMapping(value="/thymeleaf/empList.html", method=RequestMethod.GET)
	public String users(Model model){
		model.addAttribute("employees",
                new ReactiveLazyContextVariable(employeeServiceImpl.readEmployeesByDescAge()));
		return "thyme_list_emps";
	}
	
	@RequestMapping(value="/ftl/empList.html", method=RequestMethod.GET)
	public String usersFtl(Model model){
		
		model.addAttribute("employees",employeeServiceImpl.readEmployeesByDescAge().collectList().block());
		return "ftl_list_emps";
	}
}
