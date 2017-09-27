package org.packt.microservice.core.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.microservice.core.HRDeptBootApplication;
import org.packt.microservice.core.config.SpringAsynchConfig;
import org.packt.microservice.core.config.SpringDataConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes={ HRDeptBootApplication.class, CacheConfig.class, SpringDataConfig.class, SpringAsynchConfig.class })
public class TestAsyncService {
	
	@Autowired
    private MockMvc mockMvc;
        
    @Test
    public void testController () throws Exception {

        MvcResult result = mockMvc.perform(get("/callSelectDept/359.json"))
       .andExpect(request().asyncStarted())
       .andReturn();
        
        result.getRequest().getAsyncContext().setTimeout(5000);
        result.getAsyncResult();
        
        result= mockMvc.perform(asyncDispatch(result))
            .andExpect(status().isOk())
       	.andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE))
       	.andReturn();
       
        System.out.println(result.getResponse().getContentAsString());
        
    }
}
