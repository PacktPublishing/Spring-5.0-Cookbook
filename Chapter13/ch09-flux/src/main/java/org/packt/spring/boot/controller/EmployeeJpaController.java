package org.packt.spring.boot.controller;

import org.packt.spring.boot.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmployeeJpaController {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	private Logger logger = LoggerFactory.getLogger(EmployeeJpaController.class);
	
	@RequestMapping("/listEmp")
	public String employeesFTL(ModelMap model){
		logger.info("loadng JPA employee list");
		model.addAttribute("empList", employeeServiceImpl.findAllEmps());
		return "list_emps";
	}
}
