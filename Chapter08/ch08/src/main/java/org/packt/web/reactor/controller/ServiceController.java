package org.packt.web.reactor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.packt.web.reactor.model.data.Department;
import org.packt.web.reactor.model.data.Employee;
import org.packt.web.reactor.service.DepartmentService;
import org.packt.web.reactor.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import reactor.core.publisher.Flux;

@Controller
public class ServiceController {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	
	
	@RequestMapping(value="/web/employeeList.json", produces ="application/json", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public WebAsyncTask<List<Employee>> jsonEmpList(){
	   
	    Callable<List<Employee>> callable = new Callable<List<Employee>>() {
	        public List<Employee> call() throws Exception {
	            Thread.sleep(3000); 
	         
	            System.out.println("jsonEmpList task executor: " + Thread.currentThread().getName());
	            return employeeServiceImpl.readEmployees().get(5000, TimeUnit.MILLISECONDS);
	        }
	    };
	    return new WebAsyncTask<List<Employee>>(5000, callable);
	}
	
	
	@RequestMapping(value="/web/{id}/employeeDR.json", produces ="application/json", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public DeferredResult<Employee> jsonSoloEmployeeDR(@PathVariable("id") Integer id) {
	    DeferredResult<Employee> deferredResult = new DeferredResult<>();
	    deferredResult.onCompletion(() ->{
	        try {
	        	System.out.println("controller:jsonSoloEmployeeDR task executor: " + Thread.currentThread().getName());
                Thread.sleep(200);
			    deferredResult.setResult(employeeServiceImpl.readEmployee(id).get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} 
	    });
	    return deferredResult;
	}
	
		
	@RequestMapping(value="/web/{id}/employeeDRCompletable.json", produces ="application/json", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public DeferredResult<Employee> jsonSoloEmployeeDRCompletable(@PathVariable("id") Integer id) {
	    DeferredResult<Employee> deferredResult = new DeferredResult<>();
	    CompletableFuture.supplyAsync(()->{
    		try {
				return employeeServiceImpl.readEmployee(id).get(50000, TimeUnit.MILLISECONDS);
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
	
	@RequestMapping(value="/web/{id}/employeeCall.json", produces ="application/json",method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public Callable<Employee> jsonSoloEmployeeCall(@PathVariable("id") Integer id){
		Callable<Employee> task = new Callable<Employee>() {
            @Override
            public Employee call () throws Exception {
            	System.out.println("controller:jsonSoloEmployeeCall task executor: " + Thread.currentThread().getName());
                Thread.sleep(1000);
                Employee emp = employeeServiceImpl.readEmployee(id).get();
                System.out.println(emp.getLastName());
                return emp;
            }
        };
		return task;
	}
	
	@RequestMapping(value="/web/employeeListCall.json", produces ="application/json", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public Callable<List<Employee>> jsonEmpListCallable(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return employeeServiceImpl.readEmployeesCall();
	}
	
	
	@RequestMapping(value="/web/employeeNames.json", produces ="application/json",method = RequestMethod.GET,headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public Callable<List<String>> jsonEmpNames(){
		  Callable<List<String>> task = new Callable<List<String>>() {
	            @Override
	            public List<String> call () throws Exception {
	            	List<String> names = new ArrayList<>();
	            	System.out.println("controller:jsonEmpNames task executor: " + Thread.currentThread().getName());
	                Thread.sleep(5000);
	                employeeServiceImpl.readEmpFirstNames().subscribe((str)->{
	                	names.add(str);
	                });
	                return names;
	            }
		  	};
		return task;
	}
	
	// Reactor Core 
	@RequestMapping(value="/web/{id}/employeeFlux.json",produces ="application/json",method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public Flux<Employee> jsonSoloEmployeeFlux(@PathVariable("id") Integer id){
		return employeeServiceImpl.readEmployeesFlux(id);
	}
	
	@RequestMapping(value="/web/empLastnameFlux.json",produces ="application/json",method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public Flux<Employee> jsonEmpLastNameFlux(){
		return employeeServiceImpl.readEmployeesByAscLastName();
	}
	
	@RequestMapping(value="/web/empAgeFlux.json",produces ="application/json",method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public Flux<Employee> jsonEmpAgeFlux(){
		return employeeServiceImpl.readEmployeesByDescAge();
	}
	
	// RxJava Integration
	@RequestMapping(value="/web/{id}/deptSingle.json",produces ="application/json",method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public Single<Department> jsonSoloDeptSingle(@PathVariable("id") Integer id){
		return departmentServiceImpl.getDeptRx(id) ;
	}
	
	@RequestMapping(value="/web/deptList.json",produces ="application/json",method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public Observable<Department> jsonSoloDeptList(){
		return departmentServiceImpl.getDeptsRx() ;
	}
	
	@RequestMapping(value="/web/deptNames.json",produces ="application/json",method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"})
	@ResponseBody
	public Flowable<String> jsonDeptNames(){
		return departmentServiceImpl.getDepttNamesRx() ;
	}
	

}
