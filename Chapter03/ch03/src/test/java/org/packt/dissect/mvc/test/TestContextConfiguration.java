package org.packt.dissect.mvc.test;

import static org.hamcrest.CoreMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.dissect.mvc.context.SpringContextConfig;
import org.packt.dissect.mvc.dispatcher.SpringDispatcherConfig;
import org.packt.dissect.mvc.model.form.EmployeeForm;
import org.packt.dissect.mvc.webxml.SpringWebInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringWebInitializer.class, SpringDispatcherConfig.class , SpringContextConfig.class})
public class TestContextConfiguration {
	
	@Autowired 
	private WebApplicationContext ctx;
	 
	private MockMvc mockMvc;
	 
	@Before 
	public void setUp() {
	     this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testApplicaticatonContextBeans() {
	    ServletContext servletContext = ctx.getServletContext();
	    Assert.assertNotNull(servletContext);
	    Assert.assertNotNull(ctx.getBean("helloController"));
	    Assert.assertNotNull(ctx.getBean("formController"));
	}
	
	@Test
	public void testSimpleGet() throws Exception {
		mockMvc.perform(get("/simple.html")).andDo(print())   
			  .andExpect(view().name("get"));
	}
	
	@Test
	public void testFormViewPage() throws Exception {
		mockMvc.perform(get("/employee_form.html")).andDo(print())
			.andExpect(status().isOk()) 
			.andExpect(view().name("form_page")) 
			.andExpect(model().attributeExists("employeeForm"))
			.andExpect(model().attribute("employeeForm", any(EmployeeForm.class)));
	}
	
	@Test
	public void testFormSubmitPage() throws Exception {
		mockMvc.perform(post("/employee_form.html")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("firstName", "Emma")
			    .param("lastName", "Yoda")
			    .param("position", "Project Manager")
			    .param("age", "22")
			    .param("birthday", "October 30, 2001")
			    .param("email", "sjctrags@yahoo.com")
			    .accept(MediaType.APPLICATION_FORM_URLENCODED))
		        .andExpect(view().name("success_page")) 
			    .andDo(print())
	            .andExpect(status().isOk());
       }
	
	
	@Test
	public void testFormSubmitPageErrors() throws Exception {
		mockMvc.perform(post("/employee_form.html")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("firstName", "Emma")
			    .param("lastName", "Yoda")
			    .param("position", "Project Manager")
			    .param("age", "80")
			    .param("birthday", "October 30, 2001")
			    .param("email", "sjctrags@yahoo.com")
			    .accept(MediaType.APPLICATION_FORM_URLENCODED))
		        .andExpect(view().name("form_page")) 
			    .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(model().attributeHasFieldErrors("employeeForm", "age"));
       }
}
