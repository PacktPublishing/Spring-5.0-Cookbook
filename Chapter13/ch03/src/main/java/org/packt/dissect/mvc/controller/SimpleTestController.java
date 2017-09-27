package org.packt.dissect.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/simplecontroller.html")
public class SimpleTestController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String viewTransactions(){
		return "simple_list";
	}
}
