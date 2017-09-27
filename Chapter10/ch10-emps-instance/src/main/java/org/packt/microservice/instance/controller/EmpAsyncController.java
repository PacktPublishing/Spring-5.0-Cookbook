package org.packt.microservice.instance.controller;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.packt.microservice.instance.model.data.Employee;
import org.packt.microservice.instance.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;


@RestController
public class EmpAsyncController {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	@GetMapping(value="/webSyncEmpList.json", produces ="application/json", headers = {"Accept=text/xml, application/json"})
	public WebAsyncTask<List<Employee>> websyncEmpList(){
	   
	    Callable<List<Employee>> callable = new Callable<List<Employee>>() {
	        public List<Employee> call() throws Exception {
	             return employeeServiceImpl.readEmployees().get(500, TimeUnit.MILLISECONDS);
	        }
	    };
	    return new WebAsyncTask<List<Employee>>(500, callable);
	}
	
	@GetMapping(value="/deferSelectEmp/{id}.json", produces ="application/json",  headers = {"Accept=text/xml, application/json"})
	public DeferredResult<Employee> deferredSelectEmp(@PathVariable("id") Integer id) {
	    DeferredResult<Employee> deferredResult = new DeferredResult<>();
	    CompletableFuture.supplyAsync(()->{
    		try {
				return employeeServiceImpl.readEmployee(id).get();
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
	
	@GetMapping(value="/callSelectEmp/{id}.json", produces ="application/json", headers = {"Accept=text/xml, application/json"})
	public Callable<Employee> jsonSoloEmployeeCall(@PathVariable("id") Integer id){
		Callable<Employee> task = new Callable<Employee>() {
            @Override
            public Employee call () throws Exception {
            	Employee dept = employeeServiceImpl.readEmployee(id).get();
                return dept;
            }
        };
		return task;
	}

}
