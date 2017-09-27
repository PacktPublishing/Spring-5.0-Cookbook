package org.packt.web.reactor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(value = "/react/login.html", method = RequestMethod.GET)
	public String login(@RequestParam(name = "error", required = false) String error, Model model) {
		try {
			if (error.equalsIgnoreCase("true")) {
				String errorMsg = "Login Error";
				model.addAttribute("errorMsg", errorMsg);
			} else {
				model.addAttribute("errorMsg", error);
			}
		} catch (NullPointerException e) {
			return "login-form";
		}
		return "login-form";
	}

	@RequestMapping("/react/logout.html")
	public String logout() {
		return "logout";
	}

	@RequestMapping("/react/after_logout.html")
	public String afterLogout() {
		return "after-logout";
	}

	
}
