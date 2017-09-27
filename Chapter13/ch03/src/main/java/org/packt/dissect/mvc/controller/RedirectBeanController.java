package org.packt.dissect.mvc.controller;

import org.packt.dissect.mvc.model.data.Education;
import org.packt.dissect.mvc.model.data.SalaryGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectBeanController {
	@Autowired
	private SalaryGrade salaryGrade;
	
	@Autowired
	private Education education;
	
	@RequestMapping(value="/salgrade_proceed.html", method=RequestMethod.GET)
	public String processRequestBeansRedirect(Model model){
		model.addAttribute("salaryGrade", salaryGrade);
		model.addAttribute("education", education);
		return "req_proceed";
	}

}
