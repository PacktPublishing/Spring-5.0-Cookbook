package org.packt.dissect.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MultiActionController {
	
	
	@RequestMapping(value={"/send/*", "/list/*"}, method=RequestMethod.GET)
	public String defaultMethod(){
		return "default_msg";
	}
	
	@RequestMapping(value="/send/message_get.html", method=RequestMethod.GET)
	public String sendGetMessage(Model model){
		String message = "Multi-action GET URL Mapping";
		
		model.addAttribute("message", message);
		
		return "get_msg";
	}
	
	@RequestMapping(value="/send/message_post.html", method=RequestMethod.POST)
	public String sendPostMessage(Model model){
		String message = "Multi-action Post URL Mapping";
		model.addAttribute("message", message);
	    return "post_msg";	
	}
	
	@RequestMapping(value="/list/multilist.html", method=RequestMethod.GET)
	public String viewTransactions(){
		
	    return "multi_list";	
	}
	
	
	
	

}
