package org.packt.spring.boot.controller;

import org.packt.spring.boot.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.spring5.context.webflux.ReactiveLazyContextVariable;

@Controller
public class RenderController {
	
	private static Logger logger = LoggerFactory.getLogger(RenderController.class);
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	@RequestMapping(value="/thymeleaf/empList.html", method=RequestMethod.GET)
	public String users(Model model){
		logger.info("RenderController#ftlrender task started.");
		model.addAttribute("employees",
                new ReactiveLazyContextVariable(employeeServiceImpl.readEmployeesByDescAge()));
		
		return "thyme_list_emps";
	}
	
	@RequestMapping(value="/ftl/empList.html", method=RequestMethod.GET)
	public String usersFtl(Model model){
		logger.info("RenderController#thymeleafrender task started.");
		model.addAttribute("employees",employeeServiceImpl.readEmployeesByDescAge().collectList().block());
		return "ftl_list_emps";
	}
}
