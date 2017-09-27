package org.packt.microservice.core.controller;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.packt.microservice.core.model.data.Department;
import org.packt.microservice.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
public class DeptAsyncController {
	
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	@GetMapping(value="/webSyncDeptList.json", produces ="application/json", headers = {"Accept=text/xml, application/json"})
	public WebAsyncTask<List<Department>> websyncDeptList(){
	   
	    Callable<List<Department>> callable = new Callable<List<Department>>() {
	        public List<Department> call() throws Exception {
	             return departmentServiceImpl.readDepartments().get(500, TimeUnit.MILLISECONDS);
	        }
	    };
	    return new WebAsyncTask<List<Department>>(500, callable);
	}
	
	@GetMapping(value="/deferSelectDept/{id}.json", produces ="application/json",  headers = {"Accept=text/xml, application/json"})
	public DeferredResult<Department> deferredSelectDept(@PathVariable("id") Integer id) {
	    DeferredResult<Department> deferredResult = new DeferredResult<>();
	    CompletableFuture.supplyAsync(()->{
    		try {
				return departmentServiceImpl.readDepartment(id).get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			return null;
	    }).thenAccept((msg)->{
	    	deferredResult.setResult(msg);
	    });
	    return deferredResult;
	}
	
	@GetMapping(value="/callSelectDept/{id}.json", produces ="application/json", headers = {"Accept=text/xml, application/json"})
	public Callable<Department> jsonSoloEmployeeCall(@PathVariable("id") Integer id){
		Callable<Department> task = new Callable<Department>() {
            @Override
            public Department call () throws Exception {
                Department dept = departmentServiceImpl.readDepartment(id).get();
                return dept;
            }
        };
		return task;
	}
}
