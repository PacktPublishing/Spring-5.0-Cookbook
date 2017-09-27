package org.packt.web.reactor.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.packt.web.reactor.config.editor.AgeEditor;
import org.packt.web.reactor.config.editor.DateEditor;
import org.packt.web.reactor.model.data.Department;
import org.packt.web.reactor.model.data.Employee;
import org.packt.web.reactor.model.form.EmployeeForm;
import org.packt.web.reactor.service.DepartmentService;
import org.packt.web.reactor.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/react/empform.html")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	@InitBinder("employeeForm")
	public void initBinder(WebDataBinder binder){
	
		binder.registerCustomEditor(Integer.class, "age", new AgeEditor());
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String employeeForm(Model model){
		EmployeeForm employeeForm = new EmployeeForm();
		model.addAttribute("employeeForm", employeeForm);
		references(model);
		return "emp-form";
	}
		
	@RequestMapping(method=RequestMethod.POST)
	public String employeeList(Model model, @Validated @ModelAttribute("employeeForm") EmployeeForm employeeForm, BindingResult result){
		
		try {
			employeeServiceImpl.addEmployee(employeeForm);
			List<Employee> empList = employeeServiceImpl.readEmployees().get(5000, TimeUnit.SECONDS);
			model.addAttribute("empList", empList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return "emp-list";
	}
	
	private void references(Model model){
		List<Integer> deptIds = new ArrayList<>();
		List<Department> depts = departmentServiceImpl.readDepartments().getNow(new ArrayList<>());
		Iterator<Department> iterate = depts.iterator();
		while(iterate.hasNext()){
			deptIds.add(iterate.next().getId());
		}
		model.addAttribute("deptIds", deptIds);
	}
}
