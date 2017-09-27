package org.packt.starter.ioc.model.test;

import org.packt.starter.ioc.context.BeanConfig;
import org.packt.starter.ioc.model.Department;
import org.packt.starter.ioc.model.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestBeans {

	public static void main(String[] args) {
		   AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		   context.register(BeanConfig.class);
	   
		   System.out.println("application context loaded.");
	        
	       context.refresh();
	       System.out.println("*********The empRec1 bean ***************");
	       Employee empRec1 = (Employee) context.getBean("empRec1");
	       
	       System.out.println("*********The empRec2 bean ***************");
	       Employee empRec2 = (Employee) context.getBean("empRec2");
	       Department dept2 = empRec2.getDept();
	       System.out.println("First Name: " + empRec2.getFirstName());
	       System.out.println("Last Name: " + empRec2.getLastName());
	       System.out.println("Birthdate: " + empRec2.getBirthdate());
	       System.out.println("Salary: " + empRec2.getSalary());
	       System.out.println("Dept. Name: " + dept2.getDeptName());
	       
	       System.out.println("*********The empRec3 bean ***************");
	       Employee empRec3 = (Employee) context.getBean("empRec3");
	       Department dept3 = empRec3.getDept();
	       System.out.println("First Name: " + empRec3.getFirstName());
	       System.out.println("Last Name: " + empRec3.getLastName());
	       System.out.println("Birthdate: " + empRec3.getBirthdate());
	       System.out.println("Salary: " + empRec3.getSalary());
	       System.out.println("Dept. Name: " + dept3.getDeptName());
	       
	       System.out.println("*********The empRec4 bean ***************");
	       Employee empRec4 = (Employee) context.getBean("empRec4");
	       Department dept4 = empRec4.getDept();
	       System.out.println("First Name: " + empRec4.getFirstName());
	       System.out.println("Last Name: " + empRec4.getLastName());
	       System.out.println("Birthdate: " + empRec4.getBirthdate());
	       System.out.println("Salary: " + empRec4.getSalary());
	       System.out.println("Dept. Name: " + dept4.getDeptName());
	       
	       context.registerShutdownHook();
	}

}
