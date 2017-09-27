package org.packt.functional.codes.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.packt.functional.codes.dao.EmployeeDao;
import org.packt.functional.codes.model.data.Employee;
import org.packt.functional.codes.service.ComputeSalaryIncrease;
import org.packt.functional.codes.service.EmployeeRecord;
import org.packt.functional.codes.service.EmployeeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl {
	
	@Autowired
	private EmployeeDao employeeDaoImpl;
	
	public List<Employee> getEmployees(){
		
		EmployeeRecordService employees = ()->{
			
			return employeeDaoImpl.getEmployees();
		};
		
		return employees.getEmployees();
	}
	
	public Employee getEmployee(Integer empid){
		EmployeeRecord emp = new EmployeeRecord(){

			@Override
			public Employee getServiceRecord(Integer empid) {
				Iterator<Employee> iterate = employeeDaoImpl.getEmployees().iterator();
				while(iterate.hasNext()){
				   Employee e = iterate.next();
					if(e.getEmpId().equals(empid)){
						return e;
					}
				}
				
				return new Employee();
			}};
	
		return emp.getServiceRecord(empid);
	}
	
  
    
    public List<Employee> getEmployeesFunc(){
    	
    	Predicate<Employee> qualifiedEmps = (e) -> e.getAge() > 25;
    	
    	
    	List<Employee> newListEmps = new ArrayList<>();
    	Iterator<Employee> iterate = employeeDaoImpl.getEmployees().iterator();
		while(iterate.hasNext()){
		   Employee e = iterate.next();
		   if(qualifiedEmps.test(e)){
			   newListEmps.add(e);
		   }
		}
		
	   	
    	    
		return newListEmps;
	}
    
    public List<Employee> getEmployeePerDept(int deptId){
    	Predicate<Employee> qualifiedEmps = (e) -> e.getAge() > 25;
    	Predicate<Employee> groupEmp = (e) -> e.getDeptId() == deptId;
    	Predicate<Employee> rule = qualifiedEmps.and(groupEmp);
    	
    
    	List<Employee> newListEmps = new ArrayList<>();
    	Iterator<Employee> iterate = employeeDaoImpl.getEmployees().iterator();
		while(iterate.hasNext()){
		   Employee e = iterate.next();
		   if(rule.test(e)){
			   newListEmps.add(e);
		   }
		}
		
		return newListEmps;
    	
    }
	
	
	public double updateSalary(double current, double increase){
		
		ComputeSalaryIncrease salaryInc = (currSal, inc)->{
			double proRate = currSal + (inc *0.2);
			return proRate;
		};
		return salaryInc.increase(current, increase);
	}
	
	
	public double getAverageAge(){
		
		
		double avg = 0.0;
		Function<Employee, Integer> getAge = (e) -> e.getAge();
		Iterator<Employee> iterate = employeeDaoImpl.getEmployees().iterator();
		while(iterate.hasNext()){
		   Employee e = iterate.next();
			avg += getAge.apply(e);
		}
	
		
		return avg;
	}
	
	public void printEmployeeNotQuaified(){
		Consumer<Employee> showNotQualified = (e) ->{
			if(e.getAge().intValue() > 25){
				System.out.format("%s %s %s\n", e.getFirstName(), e.getLastName(), "QUALIFIED");
			}else {
				System.out.format("%s %s %s\n", e.getFirstName(), e.getLastName(), "NOT QUALIFIED");
			}
		};
		
	
		Iterator<Employee> iterate = employeeDaoImpl.getEmployees().iterator();
		while(iterate.hasNext()){
		   Employee e = iterate.next();
		   showNotQualified.accept(e);
		}
		
		
		
	}
	
	public int employeeTicket(){
		Supplier<Integer> generateTicket = () -> (int)(Math.random()*200000);
		return generateTicket.get();	
	}
	
	
	    
	
    

}
