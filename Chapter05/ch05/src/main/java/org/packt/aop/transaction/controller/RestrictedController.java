package org.packt.aop.transaction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestrictedController {
	
	
	@RequestMapping("/deptbanned.html")
	public String bannedDepts(){
		return "banned";
	}
}
