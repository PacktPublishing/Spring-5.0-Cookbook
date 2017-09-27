package org.packt.secured.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnonymousController {
	

	@RequestMapping(value="/deptanon.html")
	public String anonPage(){
		return "dept_anon";
	}

}
