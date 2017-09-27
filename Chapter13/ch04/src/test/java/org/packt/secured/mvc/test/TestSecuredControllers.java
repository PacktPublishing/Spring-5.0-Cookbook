package org.packt.secured.mvc.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.secured.mvc.context.SpringContextConfig;
import org.packt.secured.mvc.dispatcher.SpringDispatcherConfig;
import org.packt.secured.mvc.webxml.SpringWebInitializer;
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
public class TestSecuredControllers {
	
	@Autowired 
	private WebApplicationContext ctx;
		 
	private MockMvc mockMvc;
	 
	@Before 
	public void setUp() {
	     this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()).build();
	}
	
	@Test
	public void testApplicaticatonContextBeans() {
	    ServletContext servletContext = ctx.getServletContext();    
	    Assert.assertNotNull(servletContext);
	}
	
	@Test
    public void adminCanCreateOrganization() throws Exception {
        this.mockMvc.perform(get("/deptform.html")
                .with(user("sjctrags").password("sjctrags").roles("USER"))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
