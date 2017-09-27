package org.packt.functional.codes.service.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

import org.packt.functional.codes.dao.EmployeeDao;
import org.packt.functional.codes.model.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeParallelStreamService")
public class EmployeeParallelStreamService {
	
	@Autowired
	private EmployeeDao employeeDaoImpl;
	
	public void showAllEmployees(){
    	Consumer<Employee> showAll = (e) -> {
    		System.out.format("%s %s %d\n", e.getFirstName(), e.getLastName(), e.getAge());
    	};
    	employeeDaoImpl.getEmployees()
    	    .parallelStream()
    		.forEach(showAll);
    }
	
	public double getSequentialAverageAge(){
	   ToIntFunction<Employee> sizeEmpArr = (e) -> {
	    System.out.println("Thread: " + Thread.currentThread().getName());
	    	return e.getAge();
	   };
	   return employeeDaoImpl.getEmployees().stream().mapToInt(sizeEmpArr).average().getAsDouble();
	}
	
	public double getParallelAverageAge(){
	    ToIntFunction<Employee> sizeEmpArr = (e) -> {
	    	System.out.println("Thread: " + Thread.currentThread().getName());
	    	return e.getAge();
	    };
		return employeeDaoImpl.getEmployees().parallelStream().mapToInt(sizeEmpArr).average().getAsDouble();
	}
	
	public double getAverageMoreProcessors() throws InterruptedException, ExecutionException{
		ToIntFunction<Employee> sizeEmpArr = (e) -> {
		    System.out.println("Thread: " + Thread.currentThread().getName());
		    	return e.getAge();
		   };
		Callable<Double> task = () -> employeeDaoImpl.getEmployees().stream().mapToInt(sizeEmpArr).average().getAsDouble();
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		
		double avgAge = forkJoinPool.submit(task).get();
		return avgAge;
		
	}
	  

}


