package org.packt.starter.ioc.model.test;

import org.packt.starter.ioc.context.BeanConfig;
import org.packt.starter.ioc.model.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestScopes {

	public static void main(String[] args) {
		 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		   context.register(BeanConfig.class);
	   
		   System.out.println("application context loaded.");
	        
	       context.refresh();
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
	       
	       context.registerShutdownHook();
	}

}
