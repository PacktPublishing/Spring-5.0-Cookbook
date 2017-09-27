package org.packt.dissect.mvc.controller;

import org.packt.dissect.mvc.model.data.Education;
import org.packt.dissect.mvc.model.data.SalaryGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BeanScopeController {
	
	@Autowired
	private SalaryGrade salaryGrade;
	
	@Autowired
	private Education education;
	
	@RequestMapping(value="/salgrade.html", method=RequestMethod.GET)
	public String processRequestBeans(Model model){
		salaryGrade.setGrade("SG-45");
		salaryGrade.setRate(50000.00);
		education.setDegree("BS Operations Research");
		education.setMajor("Linear Algebra");
		education.setInstitution("University of the Philippines Los Banos");
		model.addAttribute("salaryGrade", salaryGrade);
		model.addAttribute("education", education);
		return "req_beans";
	}
	
	
}
