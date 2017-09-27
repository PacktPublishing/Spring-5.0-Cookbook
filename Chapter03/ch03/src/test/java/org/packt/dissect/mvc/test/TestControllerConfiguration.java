package org.packt.dissect.mvc.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.packt.dissect.mvc.controller.SimpleController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class TestControllerConfiguration {
	
	private MockMvc mockMvc;
	
	@Before 
	public void setUp() {
	     this.mockMvc = MockMvcBuilders.standaloneSetup(new SimpleController()).build();
	}
	
	@Test
	public void testGetPage() throws Exception {
		mockMvc.perform(get("/simple.html")).andDo(print())
			.andExpect(status().isOk()) 
			.andExpect(view().name("get"));
					
	}
	
	@Test
	public void testPostPage() throws Exception {
		mockMvc.perform(post("/simple.html"))
				.andExpect(view().name("post")) 
			    .andDo(print())
	            .andExpect(status().isOk());
     }
	
	@Test
	public void testOtherRequest() throws Exception {
		mockMvc.perform(get("/login.html")).andDo(print())   
			  .andExpect(view().name("login"));
	}
}
