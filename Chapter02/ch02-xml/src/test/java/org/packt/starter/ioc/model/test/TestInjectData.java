package org.packt.starter.ioc.model.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.packt.starter.ioc.model.Department;
import org.packt.starter.ioc.model.Employee;
import org.packt.starter.ioc.model.ListEmployees;
import org.packt.starter.ioc.model.MapEmpTasks;
import org.packt.starter.ioc.model.PropertiesAudition;
import org.packt.starter.ioc.model.SetDepartments;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestInjectData {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("ch02-beans.xml");
		System.out.println("application context loaded.");
	        
	    
	    ListEmployees listEmps = context.getBean("listEmployees", ListEmployees.class);
	    
	    List<Employee> empRecs = listEmps.getListEmps();
	    Iterator<Employee> iterate = empRecs.iterator();
	    System.out.println("*********Injected List of Employee record ***************");
	    while(iterate.hasNext()){
	    	Employee emp = iterate.next();
	    	System.out.format("%s  %s  %d  \n", emp.getFirstName(), emp.getLastName(),emp.getAge());
	    }
	    System.out.println("*********Injected List of Employee Names ***************");
	    List<String> empNames = listEmps.getListEmpNames();
	    Iterator<String> iterateStr = empNames.iterator();
	    while(iterateStr.hasNext()){
	    	String empName = iterateStr.next();
	    	System.out.format("%s \n", empName);
	    }
	    
	    SetDepartments setDepts = context.getBean("setDepartments", SetDepartments.class);
	    Set<Department> deptRecs = setDepts.getSetDepts();
	    Iterator<Department> deptIterate = deptRecs.iterator();
	    System.out.println("*********Injected Set of Department record ***************");
	    while(deptIterate.hasNext()){
	    	Department dept = deptIterate.next();
	    	System.out.format("%d   %s\n", dept.getDeptNo(), dept.getDeptName());
	    }
	    Set<String> deptNames = setDepts.getDeptNames();
	    Iterator<String> deptNameIterate = deptNames.iterator();
	    System.out.println("*********Injected Set of Department Names ***************");
	    while(deptNameIterate.hasNext()){
	    	String deptName = deptNameIterate.next();
	    	System.out.format("%s \n", deptName);
	    }
	    
	   
	    MapEmpTasks empTasks = context.getBean("mapEmpTasks", MapEmpTasks.class);
	    Map<String, Employee> assignTasked = empTasks.getMapEmpTask();
	    Iterator<Entry<String,Employee>> tasksIterate = assignTasked.entrySet().iterator();
	    System.out.println("*********Injected Task and Employee Mapping ***************");
	    while(tasksIterate.hasNext()){
	    	Entry<String,Employee> task = tasksIterate.next();
	    	String key = task.getKey();
	    	Employee value = task.getValue();
	    	System.out.format("Task: %s =  %s   %s  \n", key, value.getFirstName(), value.getLastName());
	    }
	    
	    System.out.println("*********Injected Task and Manager Mapping ***************");
	    Map<String,String> assignMgr = empTasks.getMapEmpMgr();
	    Iterator<Entry<String, String>> mgrIterate = assignMgr.entrySet().iterator();
	    while(mgrIterate.hasNext()){
	    	Entry<String,String> taskMgr = mgrIterate.next();
	    	String key = taskMgr.getKey();
	    	String value = taskMgr.getValue();
	    	System.out.format("Manager: %s = %s\n", value, key);
	    }
	    
	    System.out.println("*********Injected Audition Data ***************");
	    PropertiesAudition auditionInfo = context.getBean("auditionInfo", PropertiesAudition.class);
	    Properties propsAddress = auditionInfo.getAuditionAddress();
	    System.out.format("Audition Address: %s  %s   %s  %s\n", propsAddress.getProperty("building"), propsAddress.getProperty("city"), propsAddress.getProperty("country"), propsAddress.getProperty("zipcode"));
	    Properties propsReqt = auditionInfo.getAuditionRequirement();
	    System.out.format("Audition Requiremtn: Call time @ %s  bring %s and  %s  \n", propsReqt.getProperty("time"), propsReqt.getProperty("document"), propsReqt.getProperty("picture"));
	    
	}

}
