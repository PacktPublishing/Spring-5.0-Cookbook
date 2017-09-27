package org.packt.microservice.feign.controller;

import java.util.List;

import org.packt.microservice.feign.model.data.Department;
import org.packt.microservice.feign.service.DeptListClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import reactor.core.publisher.Flux;

@RestController
public class DeptFeignController {
	
	@Autowired
	private DeptListClient deptListClient;
	
	@RequestMapping(value = "/feignReactList", method = RequestMethod.GET, produces = "application/json")
    public Flux<Department> allReactiveDepts(){			    
		Flux<Department> depts = deptListClient.getDepartments();
		return depts;
      }
	
	@RequestMapping(value = "/feignBlockList", method = RequestMethod.GET, produces = "application/json")
    public List<Department> allBlockingDepts()			    {
		List<Department> depts = deptListClient.getListDepts();
		return depts;
      }
	
	@RequestMapping(value = "/feignAsyncList", method = RequestMethod.GET, produces = "application/json")
    public WebAsyncTask<List<Department>> allAsyncDepts()			    {
		WebAsyncTask<List<Department>> depts = deptListClient.getAsyncListDepts();
		return depts;
      }
}
