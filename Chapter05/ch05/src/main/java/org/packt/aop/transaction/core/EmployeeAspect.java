package org.packt.aop.transaction.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmployeeAspect {
	
	private Logger logger = Logger.getLogger(EmployeeAspect.class);
     
    @Before("execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.*(..))")
    public void logBeforeEmployeeTransactions(JoinPoint joinPoint)   {
        logger.info("EmployeeAspect.logBeforeEmployeeTransactions() detected : " + joinPoint.getSignature().getName());
    }
        
     
    @After("execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.*(..))")
    public void logAfterEmployeeTransactions(JoinPoint joinPoint)  {
        logger.info("EmployeeAspect.logAfterEmployeeTransactions() detected : " + joinPoint.getSignature().getName());
    }

}
