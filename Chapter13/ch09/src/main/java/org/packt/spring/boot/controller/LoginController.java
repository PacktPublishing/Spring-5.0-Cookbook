package org.packt.spring.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

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
			logger.info("LoginController#form task started.");
			return "login-form";
		}
		logger.info("LoginController#form task started.");
		return "login-form";
	}

	@RequestMapping("/react/logout.html")
	public String logout() {
		logger.info("LoginController#logout task started.");
		return "logout";
	}

	@RequestMapping("/react/after_logout.html")
	public String afterLogout() {
		logger.info("LoginController#afterlogout task started.");
		return "after-logout";
	}

	
}
