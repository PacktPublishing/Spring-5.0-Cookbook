package org.packt.spring.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FluxController {
	
	private Logger logger = LoggerFactory.getLogger(FluxController.class);
	
    @RequestMapping("/sampleFtl")
    public String home(ModelMap model) {
        model.addAttribute("title", "Reactive FreeMarker Result");
        model.addAttribute("message", "Built-in Configuration");
        logger.info("exceuting HelloController");
        return "sampleFtl";
    }
    
    @RequestMapping("/thymeleaf/sampleThyme")
	public String welcome(ModelMap model) {
    	model.addAttribute("title", "Reactive Thymeleaf Result");
        model.addAttribute("message", "Built-in Configuration");
		return "sampleThyme";
	}

}
