package org.packt.starter.ioc.model.test;

import org.packt.starter.ioc.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestScopes {

	public static void main(String[] args) {
		   ApplicationContext context = new ClassPathXmlApplicationContext("ch02-beans.xml");
		   System.out.println("application context loaded.");
	        
	       System.out.println("*********The empRec1 bean ***************");
	       Employee empRec1A = (Employee) context.getBean("empRec1");
	       System.out.println("instance A: " + empRec1A.hashCode());
	       Employee empRec1B = (Employee) context.getBean("empRec1");
	       System.out.println("instance B: " +empRec1B.hashCode());
	       
	       System.out.println("*********The empRec2 bean ***************");
	       Employee empRec2A = (Employee) context.getBean("empRec2");
	       System.out.println("instance A: " + empRec2A.hashCode());
	       Employee empRec2B = (Employee) context.getBean("empRec2");
	       System.out.println("instance B: " + empRec2B.hashCode());
	       
	       
	}

}
