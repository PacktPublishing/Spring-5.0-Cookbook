package org.packt.dissect.mvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.packt.dissect.mvc.model.form.FileUploadForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/simple")
public class SimplePatternsController {
	
	@RequestMapping(value="/form_upload_get.html", method=RequestMethod.GET)
	public String uploadFileFormGet(Model model) {
		FileUploadForm fileUploadForm = new FileUploadForm();
		model.addAttribute("fileUploadForm", fileUploadForm);
	    return "put_form";
	}
	
	@RequestMapping(value="/form_upload_post.html", method=RequestMethod.POST)
	public String uploadFileFormPost(Model model) {
		// these two can be deleted if necessary
		FileUploadForm fileUploadForm = new FileUploadForm();
		model.addAttribute("fileUploadForm", fileUploadForm);
	    return "put_form";
	}
	
	
	@RequestMapping(value="/upload.html",method={RequestMethod.PUT, RequestMethod.POST})
	public String uploadFileSubmit(Model model, @ModelAttribute("fileUploadForm") FileUploadForm fileUploadForm, HttpServletRequest req) {
		String fileName = fileUploadForm.getFile().getOriginalFilename();
	   	   
	    String transactionType = "Simple PUT Transaction";
		model.addAttribute("transactionType", transactionType);
		model.addAttribute("fileName", fileName);
	     
	    return "put_result";
	}
	
	
	
	@RequestMapping(value="/patterns.html", method=RequestMethod.GET)
	public String uploadFileForm() {
		
	    return "simple_patterns";
	}
	
	@RequestMapping(value="/delete.html", method={RequestMethod.DELETE, RequestMethod.GET})
	public String deleteEvent(Model model){
		
		String transactionType = "Simple DELETE Transaction";
		model.addAttribute("transactionType", transactionType);
		return "delete";
	}
}
