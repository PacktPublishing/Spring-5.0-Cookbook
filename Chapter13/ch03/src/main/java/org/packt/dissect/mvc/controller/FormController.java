package org.packt.dissect.mvc.controller;

import java.util.Date;

import org.packt.dissect.mvc.editor.AgeEditor;
import org.packt.dissect.mvc.editor.DateEditor;
import org.packt.dissect.mvc.model.form.EmployeeForm;
import org.packt.dissect.mvc.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/employee_form.html")
public class FormController {
	
	@Autowired
	private Validator employeeValidator;
	
	@Autowired
	private LocalValidatorFactoryBean validator;
	
	@InitBinder("employeeForm")
	public void initBinder(WebDataBinder binder){
		binder.setValidator(employeeValidator);
		
		binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(Integer.class, "age", new AgeEditor());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String initForm(Model model){
		EmployeeForm employeeForm = new EmployeeForm();
		model.addAttribute("employeeForm", employeeForm);
		return "form_page";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submitForm(Model model, @ModelAttribute("employeeForm") @Validated EmployeeForm employeeForm, BindingResult result ){
		model.addAttribute("employeeForm", employeeForm);
		validator.validate(employeeForm, result);
		if(result.hasErrors()){
			return "form_page";
		}
		return "success_page";
	}
	
	
}
