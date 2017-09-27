package org.packt.dissect.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/simple.html")
public class SimpleController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String processGetReq(Model model){
		String transactionType = "Simple GET Transaction";
		model.addAttribute("transactionType", transactionType);
		return "get";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processPostReq(Model model){
		String transactionType = "Simple POST Transaction";
		model.addAttribute("transactionType", transactionType);
		return "post";
	}
}
