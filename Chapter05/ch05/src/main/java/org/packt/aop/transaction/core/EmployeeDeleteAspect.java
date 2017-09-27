package org.packt.aop.transaction.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmployeeDeleteAspect {
	
    private Logger logger = Logger.getLogger(EmployeeDeleteAspect.class);
	
	@Before("execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.delEmployee(..)) && args(empId)")
    public void logBeforeDeleteEmp(JoinPoint joinPoint, Integer empId)  {
        logger.info("EmployeeDeleteAspect.logBeforeDeleteEmp() detected : " + joinPoint.getSignature().getName() + " with " + empId);
    }
	
	@After("execution(* org.packt.aop.transaction.service.impl.EmployeeServiceImpl.delEmployee(..)) && args(empId)")
    public void logAfterDeleteEmp(JoinPoint joinPoint, Integer empId)  {
        logger.info("EmployeeDeleteAspect.logAfterDeleteEmp() detected: " + joinPoint.getSignature().getName() + " with " + empId);
    }


}
