package org.packt.aop.transaction.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmployeeReadAspect {

	private Logger logger = Logger.getLogger(EmployeeReadAspect.class);

	@Before("execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.readEmployees(..))")
	public void logBeforeReadEmp(JoinPoint joinPoint) {
		logger.info("EmployeeReadAspect.logBeforeReadEmp() detected: " + joinPoint.getSignature().getName());
	}

	@After("execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.readEmployees(..))")
	public void logAfterReadEmp(JoinPoint joinPoint) {
		logger.info("EmployeeReadAspect.logAfterAfterEmp() detected: " + joinPoint.getSignature().getName());
	}
	
	
	@Before("execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.readEmployee(..)) && args(empId)")
	public void logBeforeReadOneEmp(JoinPoint joinPoint, Integer empId) {
		logger.info("EmployeeReadAspect.logBeforeReadOneEmp() detected: " + joinPoint.getSignature().getName() + "with " + empId);
	}

	@After("execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.readEmployee(..)) && args(empId)")
	public void logAfterReadOneEmp(JoinPoint joinPoint, Integer empId) {
		logger.info("EmployeeReadAspect.logAfterReadOneEmp() detected: " + joinPoint.getSignature().getName() + "with " + empId);
	}

}
