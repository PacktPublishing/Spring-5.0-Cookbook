package org.packt.starter.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String myTest(){
		return "testing";
	}

}
