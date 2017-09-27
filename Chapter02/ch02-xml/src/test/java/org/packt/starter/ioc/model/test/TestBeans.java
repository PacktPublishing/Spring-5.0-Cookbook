package org.packt.starter.ioc.model.test;

import org.packt.starter.ioc.model.Department;
import org.packt.starter.ioc.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestBeans {
	public static void main(String args[]){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("ch02-beans.xml");
        System.out.println("application context loaded.");
        
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
        
        System.out.println("*********The empRec5 bean ***************");
        Employee empRec5 = context.getBean("empRec5", Employee.class);
        Department dept3 = empRec5.getDept();
        System.out.println("First Name: " + empRec5.getFirstName());
        System.out.println("Last Name: " + empRec5.getLastName());
        System.out.println("Dept. Name: " + dept3.getDeptName());
        
        System.out.println("*********The empRec6 Lazy bean ***************");
        Employee empRec6 = context.getBean("empRec6", Employee.class);
        Department dept4 = empRec6.getDept();
	}  
}
