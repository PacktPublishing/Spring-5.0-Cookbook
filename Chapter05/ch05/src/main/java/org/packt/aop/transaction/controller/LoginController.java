package org.packt.aop.transaction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.packt.aop.transaction.model.data.Employee;
import org.packt.aop.transaction.service.EmployeeService;
import org.packt.aop.transaction.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	@RequestMapping(value="/login_emps.html", method=RequestMethod.GET)
	public String login(Model model, HttpServletRequest req){
		int browserNo = (Integer) req.getAttribute("browserNo");
		if(browserNo == 3){
			model.addAttribute("error", "Browser Not Supported");
			return "browser_error";
		}
		return "login";
		
	}
	
	@RequestMapping(value="/login_emps.html", method=RequestMethod.POST)
	public ModelAndView loginSubmit(ModelMap model, @RequestParam("username") String username, @RequestParam("password") String password){
		
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		return new ModelAndView("redirect:/menu_emps.html",model);
		
	}
	
	@RequestMapping(value="/menu_emps.html", method=RequestMethod.GET)
	public String menu(Model model, HttpServletRequest req){
		
		List<Employee> emps = employeeServiceImpl.readEmployees();
		model.addAttribute("emps", emps);
		return "menu";
	}
	
	
	@RequestMapping(value="/empty_login.html", method=RequestMethod.GET)
	public String emptylogin(){
		return "empty_login";
	}
	
	@RequestMapping(value="/browser_error.html", method=RequestMethod.GET)
	public String browserError(){
		return "browser_error";
		
	}
	
}
