package org.packt.secured.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionController {
	

	@RequestMapping("/session_expired.html")
	public String sessionExpired(){
		return "session_expired";
	}
	
	@RequestMapping("/session_invalid.html")
	public String sessionInvalid(){
		return "session_expired";
	}
	
	

}
