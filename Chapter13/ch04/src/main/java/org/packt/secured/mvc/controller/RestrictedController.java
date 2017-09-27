package org.packt.secured.mvc.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestrictedController {
	
	@PreAuthorize("hasRole('HR') OR hasRole('ADMIN')")
	@RequestMapping("/deptbanned.html")
	public String bannedDepts(){
		return "banned";
	}
}
