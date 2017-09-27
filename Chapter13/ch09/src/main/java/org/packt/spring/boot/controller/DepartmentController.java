package org.packt.spring.boot.controller;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.packt.spring.boot.model.data.Department;
import org.packt.spring.boot.model.form.DepartmentForm;
import org.packt.spring.boot.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DepartmentController {
	
	private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentServiceImpl;

	@RequestMapping(value="/react/deptform.html", method=RequestMethod.GET)
	public String initForm(Model model){
		logger.info("DepartmentController#form task started.");
		DepartmentForm departmentForm = new DepartmentForm();
		model.addAttribute("departmentForm", departmentForm);
	    return "dept-form";	
	}
	
	@RequestMapping(value="/react/deptform.html", method=RequestMethod.POST)
	public String submitForm(Model model, @ModelAttribute("departmentForm") DepartmentForm departmentForm){
		try {
			Thread.sleep(5000);
			logger.info("DepartmentController#formsubmit task started.");
			departmentServiceImpl.addDepartment(departmentForm);
			model.addAttribute("departments", departmentServiceImpl.readDepartments().get(5000, TimeUnit.MILLISECONDS));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	
		
		return "dept-list";
	}
	

	@RequestMapping(value={"/react/deldept.html/{deptId}"})
	public String deleteRecord(Model model, @PathVariable("deptId") Integer deptId){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		departmentServiceImpl.removeDepartment(deptId);
		model.addAttribute("departments", departmentServiceImpl.readDepartments().getNow(new ArrayList<>()));
		return "dept-list";
	}
	
	@RequestMapping(value={"/react/updatedept.html/{id}"})
	public String updateRecord(Model model, @PathVariable("id") Integer id){
		ExecutorService threads = Executors.newFixedThreadPool(5);
		Future<Department> deptfuture = threads.submit(departmentServiceImpl.getDeptId(id));
		Department dept = null;
		while (!deptfuture.isDone()) { 
			System.out.println("Thread is still busy processing...."); 
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			} 
		}

		try {
			dept = deptfuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DepartmentForm departmentForm = new DepartmentForm();
		departmentForm.setDeptId(dept.getDeptId());
		departmentForm.setName(dept.getName());
		model.addAttribute("departmentForm", departmentForm);
		return "dept-form";
	}
	
	@RequestMapping(value={"/react/updatedept.html/{id}"}, method=RequestMethod.POST)
	public String updateRecordUpdate(Model model, @PathVariable("id") Integer id, @ModelAttribute("departmentForm") DepartmentForm departmentForm){
	
		try {
			departmentServiceImpl.updateDepartment(departmentForm, id);
			Thread.sleep(5000);
			departmentServiceImpl.addDepartment(departmentForm);
			model.addAttribute("departments", departmentServiceImpl.readDepartments().get(5000, TimeUnit.MILLISECONDS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "dept-list";
	}
}
