package org.packt.secured.mvc.test;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.secured.mvc.context.SpringContextConfig;
import org.packt.secured.mvc.dispatcher.SpringDispatcherConfig;
import org.packt.secured.mvc.model.data.Department;
import org.packt.secured.mvc.service.DepartmentService;
import org.packt.secured.mvc.webxml.SpringWebInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringWebInitializer.class, SpringDispatcherConfig.class , SpringContextConfig.class})
public class TestSecuredServices {
	
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	@Autowired 
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	 
	@Before 
	public void setUp() {
	     this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).apply(springSecurity()).build();
	}
	
	@Test
	@WithMockUser(roles="USER")
	public void testListDepts(){
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));
		List<Department> depts = departmentServiceImpl.readDepartments();
		assertNotNull(depts);
		for(Department dept : depts){
			System.out.println(dept.getDeptId());
		}
	}

}
