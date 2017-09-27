package org.packt.aop.transaction.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.packt.aop.transaction.model.form.EmployeeForm;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmployeeUpdateAspect {
	
	private Logger logger = Logger.getLogger(EmployeeUpdateAspect.class);
	
	@Before("execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.updateEmployee(..)) && args(empForm, id)")
    public void logBeforeUpdateEmp(JoinPoint joinPoint, EmployeeForm empForm, int id)  {
        logger.info("EmployeeUpdateAspect.logBeforeUpdateEmp() : " + joinPoint.getSignature().getName() + " with record id: " + id);
        
    }
	
	@After("execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.updateEmployee(..)) && args(empForm, id)")
    public void logAfterUpdateEmp(JoinPoint joinPoint, EmployeeForm empForm, int id)  {
        logger.info("EmployeeUpdateAspect.logAfterUpdateEmp() : " + joinPoint.getSignature().getName() + " with record id: " + id);
    }

}
