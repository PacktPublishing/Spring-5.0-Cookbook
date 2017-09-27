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
		return "dept_success";
	}
	
	@RequestMapping(value="/dept_form.html", method=RequestMethod.GET)
	public String deptFormLoad(){
		return "dept_form";
	}
	
	@RequestMapping(value="/page_path.html/id/{id}/name/{empName}", method=RequestMethod.GET)
	public String requestEmployeePaths(Model model, @PathVariable("id") Integer id, @PathVariable("empName") String empName){
		return "page_path";
	}
	
	@RequestMapping(value="/validate_cookies.html", method=RequestMethod.POST)
	public String validateCookies(Model model, @CookieValue("serial") String serial, @CookieValue("license") String license){
		return "cookie_page";
	}
	
	@RequestMapping(value = "*",  method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String broadcast() {
			    return "Fallback for All Requests";
	}
	
	@RequestMapping("/hi")
	 public void hostInfo(@RequestHeader("Host") String host)  {

	

	}
	
	@RequestMapping(value="/fetch", params = {"id=100"})
	public String getInfo1(@RequestParam("id") String id) {
		System.out.println("Inside getInfo1");
		return "success";
	}
	@RequestMapping(value="/fetch", params = {"id=200"})
	public String getInfo2(@RequestParam("id") String id) {
		System.out.println("Inside getInfo2");
		return "success";
	}

}
