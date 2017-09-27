package org.packt.dissect.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectPageController {
	
	@RequestMapping(value="/login.html", method=RequestMethod.GET)
	public String login(){
		
		return "login";
	}
	
	@RequestMapping(value="/jump_page.html", method=RequestMethod.POST)
	public RedirectView sendRedirection(RedirectAttributes atts, @RequestParam("username") String username, 
			@RequestParam("password") String password){
		atts.addFlashAttribute("username", username);
		atts.addFlashAttribute("password", password);
		atts.addAttribute("request", "loginForm");
		return new RedirectView("/redirectviewOld.html",true);
	}
	
	@RequestMapping(value="/redirectviewOld.html", method=RequestMethod.GET)
	public String resultPageOld(Model model ){
		
		return "result_page";
	}
	
		
	@RequestMapping(value="/new_jump.html", method=RequestMethod.GET)
	public ModelAndView sendRedirectionModel(ModelMap atts){
		atts.addAttribute("pageId_flash", "12345");
		
		return new ModelAndView("redirect:/redirectviewNew.html",atts);
	}
	
	
	@RequestMapping(value="/redirectviewNew.html", method=RequestMethod.GET)
	public String resultPageNew(Model model, @ModelAttribute("pageId_flash") String flash){
		model.addAttribute("pageId_flash", flash);
		return "new_result_page";
	}
	
	

}
