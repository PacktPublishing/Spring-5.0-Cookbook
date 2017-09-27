package org.packt.microservice.hystrix.controller;

import java.util.List;

import org.packt.microservice.hystrix.model.data.Department;
import org.packt.microservice.hystrix.model.data.Employee;
import org.packt.microservice.hystrix.model.data.UserDetails;
import org.packt.microservice.hystrix.service.DeptHystrixService;
import org.packt.microservice.hystrix.service.EmpHystrixService;
import org.packt.microservice.hystrix.service.GreetingService;
import org.packt.microservice.hystrix.service.LoginHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.core.publisher.Mono;

@Controller
public class HystrixClientController {

    @Autowired
    private DeptHystrixService deptHystrixService;
    
    @Autowired
    private EmpHystrixService empHystrixService;
    
    @Autowired
    private LoginHystrixService logniHystrixService;
 
	
	 @Autowired
	 private GreetingService greetingService;
	    
    @RequestMapping("/get-greeting/{username}")
    public String getGreeting(Model model, @PathVariable("username") String username) {
        model.addAttribute("greeting", greetingService.getGreeting());
        return "greeting-view";
    }
   
    @RequestMapping("/hystrixUsers")
    @ResponseBody
    public List<UserDetails> hystrixGetUsers(){
    	return logniHystrixService.getLoginUsers();
    }
    
    
    @RequestMapping("/hystrixGetEmp/{id}")
    @ResponseBody
    public Employee hystrixSelectEmp(@PathVariable("id") Integer id){
    	return empHystrixService.getAsyncEmp(id);
    }
    
    
    @RequestMapping("/hystrixGetDept/{id}")
    @ResponseBody
    public Mono<Department> hystrixSelectDept(@PathVariable("id") Integer id){
    	return deptHystrixService.getMonoDept(id);
    }
    
   
    
}