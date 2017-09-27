package org.packt.spring.boot.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

import org.packt.spring.boot.model.data.Department;
import org.packt.spring.boot.model.data.Employee;
import org.packt.spring.boot.model.form.EmployeeForm;
import org.packt.spring.boot.service.DepartmentService;
import org.packt.spring.boot.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportController {
	
	private static Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	@RequestMapping(value="/react/menu.html", method=RequestMethod.GET)
	public String menu(){
		logger.info("ReportController#menu task started.");
		return "menu";
	}
	
	@RequestMapping(value="/react/viewdepts.html", method=RequestMethod.GET)
	public String viewDepts(Model model){
		
		try {
			logger.info("ReportController#viewDepts task started.");
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
	
	
	@RequestMapping(value="/react/viewemps.html", method=RequestMethod.GET)
	public String viewEmps(Model model){
		logger.info("ReportController#viewEmps task started.");
		List<Employee> empList = employeeServiceImpl.readEmployees().join();
		model.addAttribute("empList", empList);
		return "emp-list";
	}
	
	@RequestMapping(value={"/react/delemp.html/{empId}"})
	public String deleteRecord(Model model, @PathVariable("empId") Integer empId){
		
		try {
			logger.info("ReportController#deleteRecord task started.");
			employeeServiceImpl.delEmployee(empId);
			Thread.sleep(1000);
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
	
	@RequestMapping(value={"/react/updateemp.html/{id}"})
	public String updateRecord(Model  model, @PathVariable("id") Integer id){
		try {
			logger.info("ReportController#updateRecord task started.");
			Employee emp = employeeServiceImpl.readEmployee(id).get(5000,TimeUnit.MILLISECONDS);
			EmployeeForm employeeForm = new EmployeeForm();
			employeeForm.setFirstName(emp.getFirstName());
			employeeForm.setLastName(emp.getLastName());
			employeeForm.setAge(emp.getAge());
			employeeForm.setEmail(emp.getEmail());
			employeeForm.setBirthday(emp.getBirthday());
			employeeForm.setDeptId(emp.getDeptId());
			employeeForm.setEmpId(emp.getEmpId());	
			model.addAttribute("employeeForm", employeeForm);
			references(model);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		
		return "emp-form";
	}
	
	@RequestMapping(value={"/react/updateemp.html/{id}"}, method=RequestMethod.POST)
	public String updateRecordSubmit(Model  model, @PathVariable("id") Integer id, @Validated @ModelAttribute("employeeForm") EmployeeForm employeeForm, BindingResult result){
		Consumer<List<Employee>> processResult = (empList) ->{
			model.addAttribute("empList", empList);
		};
		CompletableFuture.supplyAsync(()->{
	    		try {
	    			logger.info("ReportController#updateRecordSubmit task started.");
	    			employeeServiceImpl.updateEmployee(employeeForm, id);
	    			Thread.sleep(1000);
					return employeeServiceImpl.readEmployees().get(5000, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (TimeoutException e) {
					e.printStackTrace();
				}
				return null;
	    }).thenAccept(processResult);
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
