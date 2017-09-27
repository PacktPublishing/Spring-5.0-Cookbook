package org.packt.functional.codes.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.packt.functional.codes.model.data.Employee;
import org.springframework.stereotype.Service;


@Service("employeeDataService")
public class EmployeeDataService {
	
	public Employee createEmployee(){
		Supplier<Employee> newEmp = Employee::new;
		
		return newEmp.get();
	}
	
	public List<Employee> startList(){
		Supplier<List<Employee>> newList = ArrayList::new;
		return newList.get();
	}
	
	public Date convertBday(long bdayLong){
		Function<Long, Date> bday = Date::new;
		return bday.apply(bdayLong);
	}
	
	
		
}
