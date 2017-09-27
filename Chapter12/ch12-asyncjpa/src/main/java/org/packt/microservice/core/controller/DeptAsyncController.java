package org.packt.microservice.core.controller;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.packt.microservice.core.model.data.Department;
import org.packt.microservice.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
public class DeptAsyncController {
	
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	private Department result = new Department();
		
	@GetMapping(value="/webSyncDept/{id}.json", produces ="application/json", headers = {"Accept=text/xml, application/json"})
	public WebAsyncTask<Department> websyncDeptList(@PathVariable("id") Integer id){
	   
	    Callable<Department> callable = new Callable<Department>() {
	    	public Department call() throws Exception {
	    		
	    		 ListenableFuture<Department> listenFuture = departmentServiceImpl.findAllFirstById(id);
	    		 listenFuture.addCallback(new ListenableFutureCallback<Department>(){

					@Override
					public void onSuccess(Department dept) {
						result = dept;
					}

					@Override
					public void onFailure(Throwable arg0) {
						result = new Department();
					}
	    			 
	    		 });
	    		 return result;
	          }
	    };
	    return new WebAsyncTask<Department>(500, callable);
	}
	
	@GetMapping(value="/deferSelectDept/{name}.json", produces ="application/json",  headers = {"Accept=text/xml, application/json"})
	public DeferredResult<Department> deferredSelectDept(@PathVariable("name") String name) {
	    DeferredResult<Department> deferredResult = new DeferredResult<>();
	    CompletableFuture.supplyAsync(()->{
    		try {
				return departmentServiceImpl.findAllFirstByNameIgnoreCase(name).get(500, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	    }).thenAccept((msg)->{
	    	deferredResult.setResult(msg);
	    });
	    return deferredResult;
	}
	
	@GetMapping(value="/callSelectDept/{deptId}.json", produces ="application/json", headers = {"Accept=text/xml, application/json"})
	public Callable<List<Department>> jsonSoloEmployeeCall(@PathVariable("deptId") Integer deptId){
		Callable<List<Department>> task = new Callable<List<Department>>() {
            @Override
            public List<Department> call () throws Exception {
            	List<Department> dept = departmentServiceImpl.findAllByDeptId(deptId).get(500, TimeUnit.MILLISECONDS);
                return dept;
            }
        };
		return task;
	}
	
	
}
