package org.packt.aop.transaction.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmployeeInsertAspect {
	
    private Logger logger = Logger.getLogger(EmployeeInsertAspect.class);
	
	@Before("execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.addEmployee(..))")
    public void logBeforeInsertEmp(JoinPoint joinPoint)  {
        logger.info("EmployeeInsertAspect.logBeforeInsertEmp() detected: " + joinPoint.getSignature().getName());
    }
	
	@After("execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.addEmployee(..))")
    public void logAfterInsertEmp(JoinPoint joinPoint)  {
        logger.info("EmployeeInsertAspect.logAfterInsertEmp() detected: " + joinPoint.getSignature().getName());
    }

}
