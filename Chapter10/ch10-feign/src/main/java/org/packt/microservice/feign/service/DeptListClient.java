package org.packt.microservice.feign.service;

import java.util.List;

import org.packt.microservice.feign.model.data.Department;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.WebAsyncTask;

import reactor.core.publisher.Flux;

@FeignClient(name = "department-feign", url = "http://localhost:8090/ch10-dept")
public interface DeptListClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/selectReactDepts" )
    public Flux<Department> getDepartments();
	
	@RequestMapping(method = RequestMethod.GET, value = "/listDept" )
    public List<Department> getListDepts();
	
	@RequestMapping(method = RequestMethod.GET, value = "/webSyncDeptList.json" )
	public WebAsyncTask<List<Department>> getAsyncListDepts();
}
