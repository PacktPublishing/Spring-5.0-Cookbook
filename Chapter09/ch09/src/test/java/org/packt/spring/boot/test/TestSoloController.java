package org.packt.spring.boot.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.spring.boot.HRBootApplication;
import org.packt.spring.boot.config.SpringAsynchConfig;
import org.packt.spring.boot.config.SpringContextConfig;
import org.packt.spring.boot.config.SpringMvcConfig;
import org.packt.spring.boot.controller.DepartmentController;
import org.packt.spring.boot.service.DepartmentService;
import org.packt.spring.boot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
public class TestSoloController {
	
	@Autowired
	 private MockMvc mvc;
	
	 @MockBean
	 private DepartmentService departmentServiceImpl;
	 
	 @Test
	 public void callEmpFormReq() throws Exception   {
	  
	    mvc.perform(get("/react/deptform.html")
	      .with(user("sjctrags").password("sjctrags").roles("USER")))
	      .andDo(print())
	      .andExpect(status().isOk());
	 }

}
