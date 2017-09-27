package org.packt.aop.transaction.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.packt.aop.transaction.model.data.Employee;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DetectNullsAspect {
	
	private Logger logger = Logger.getLogger(DetectNullsAspect.class);
	
	@AfterReturning(pointcut="execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.readEmployees(..))", returning="emps")
	public void detectNullEmps(JoinPoint joinPoint, List emps) {
	   	  logger.info("DetectNullsAspect.detectNullEmps() detected : " + joinPoint.getSignature().getName());
	   
		 if(emps == null){
			 logger.info("DetectNullsAspect.safeReadEmps() passes : empty " + emps);
		 }else{
			 logger.info("DetectNullsAspect.safeReadEmps() passes : " + emps);
		 }
	}

	
	
	@AfterReturning(pointcut="execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.readEmployee(..))", returning="emp")
	public void detectNullOneEmp(JoinPoint joinPoint, Employee emp) {
		logger.info("DetectNullsAspect.detectNullOneEmp() detected : " + joinPoint.getSignature().getName());
		 if(emp == null){
			 logger.info("DetectNullsAspect.safeReadEmps() passes : empty " + emp);
		 }else{
			 logger.info("DetectNullsAspect.safeReadEmps() passes : " + emp);
		 }
		
	}

	


}
