package org.packt.dissect.mvc.controller;

import org.packt.dissect.mvc.model.form.DepartmentForm;
import org.packt.dissect.mvc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DepartmentController {
	
	
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	@RequestMapping("/deptform.html")
	public String initForm(Model model){
		
		DepartmentForm departmentForm = new DepartmentForm();
		model.addAttribute("departmentForm", departmentForm);
	    return "dept_form";	
	}
	
	@RequestMapping(value="/deptform.html", method=RequestMethod.POST)
	public String submitForm(Model model, @ModelAttribute("departmentForm") DepartmentForm departmentForm){
		departmentServiceImpl.addDepartment(departmentForm);
		model.addAttribute("departments", departmentServiceImpl.readDepartments());
		return "dept_result";
	}

}
