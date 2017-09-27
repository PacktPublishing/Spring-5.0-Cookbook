package org.packt.secured.mvc.controller;

import org.packt.secured.mvc.service.DepartmentService;
import org.packt.secured.mvc.model.data.Department;
import org.packt.secured.mvc.model.form.DepartmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	@RequestMapping(value={"/deptform.html", "/custom/deptform.html"}, method=RequestMethod.GET)
	public String initForm(Model model){
		
		DepartmentForm departmentForm = new DepartmentForm();
		model.addAttribute("departmentForm", departmentForm);
		
	    return "dept_form";	
	}
	
	@RequestMapping(value={"/deptform.html", "/custom/deptform.html"}, method=RequestMethod.POST)
	public String submitForm(Model model, @ModelAttribute("departmentForm") DepartmentForm departmentForm){
		departmentServiceImpl.addDepartment(departmentForm);
		model.addAttribute("departments", departmentServiceImpl.readDepartments());
		return "dept_result";
	}
	

	@RequestMapping(value={"/deldept.html/{deptId}", "/custom/deldept.html/{deptId}"})
	public String deleteRecord(Model model, @PathVariable("deptId") Integer deptId){
		departmentServiceImpl.removeDepartment(deptId);
		model.addAttribute("departments", departmentServiceImpl.readDepartments());
		return "dept_result";
	}
	
	@RequestMapping(value={"/updatedept.html/{id}","/custom/updatedept.html/{id}"})
	public String updateRecord(Model model, @PathVariable("id") Integer id){
		Department dept = departmentServiceImpl.getDeptId(id);
		DepartmentForm departmentForm = new DepartmentForm();
		departmentForm.setDeptId(dept.getDeptId());
		departmentForm.setName(dept.getName());
		
		model.addAttribute("departmentForm", departmentForm);
		return "dept_form";
	}
	
	@RequestMapping(value="/updatedept.html/{id}", method=RequestMethod.POST)
	public String updateRecordSubmit(Model model, @ModelAttribute("departmentForm") DepartmentForm departmentForm, @PathVariable("id") Integer id ){
		
		departmentServiceImpl.updateDepartment(departmentForm, id);
				
		model.addAttribute("departments", departmentServiceImpl.readDepartments());
		return "dept_result";
	}
}
