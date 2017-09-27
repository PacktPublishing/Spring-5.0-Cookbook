package org.packt.aop.transaction.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.packt.aop.transaction.model.form.EmployeeForm;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionUpdateAspect {
	
	private Logger logger = Logger.getLogger(ExceptionUpdateAspect.class);
	
	@AfterThrowing(pointcut="execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.updateEmployee(..)) && args(empForm,id)", throwing = "e")
    public void logExceptionUpdateEmp(JoinPoint joinPoint, EmployeeForm empForm, int id, Throwable e) throws Throwable {
       logger.info("ExceptionUpdateAspect.logExceptionUpdateEmp() : " + joinPoint.getSignature().getName() + " with record id: " + id);
       logger.info("Exception encountered: " + e.getMessage());
	}
	
	@AfterThrowing(pointcut="within(org.packt.aop.transaction.service.impl.EmployeeServiceImpl)", throwing = "e")
    public void logExceptionEmployee(JoinPoint joinPoint, Throwable e) throws Throwable {
       logger.info("ExceptionUpdateAspect.logExceptionUpdateEmp() : " + joinPoint.getSignature().getName() );
       logger.info("Exception encountered: " + e.getMessage());
	}
}
