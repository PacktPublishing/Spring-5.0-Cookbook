package org.packt.dissect.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestHandlingController {
	
	// Other ways of Form handling
	@RequestMapping(value="/dept_form.html", method=RequestMethod.POST)
	public String deptFormSubmit(Model model, @RequestParam("deptId") Integer deptId, 
											@RequestParam("deptName") String deptName){
		model.addAttribute("deptId", deptId);
		model.addAttribute("deptName", deptName);
		return "dept_success";
	}
	
	@RequestMapping(value="/dept_form.html", method=RequestMethod.GET)
	public String deptFormLoad(){
		return "dept_form";
	}
	
	@RequestMapping(value = "*",  method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String broadcast() {
			    return "Fallback for All Requests";
	}
	
	
}
