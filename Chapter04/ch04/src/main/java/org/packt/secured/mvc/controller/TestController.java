package org.packt.secured.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/test1.html")
	public String testPage(){
		
		return "test";
	}
	
	@RequestMapping("/test2.html")
	public String testPage2(){
		
		return "mytest";
	}

}
