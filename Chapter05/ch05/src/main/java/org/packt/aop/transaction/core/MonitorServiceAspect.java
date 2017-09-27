package org.packt.aop.transaction.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.packt.aop.transaction.annotation.MonitorService;
import org.packt.aop.transaction.model.data.Employee;
import org.packt.aop.transaction.service.EmployeeService;
import org.packt.aop.transaction.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@Aspect
public class MonitorServiceAspect {
	
	@Autowired
    private TransactionTemplate template;

    private Logger logger = Logger.getLogger(MonitorServiceAspect.class);

    @Around("execution(* *(..)) && @annotation(monitor)")
    public void logIt(ProceedingJoinPoint pjp, MonitorService monitor) {
    	template.execute(s->{
            try{
                Employee employee = (Employee) pjp.proceed();
                logger.info(employee.getFirstName());
            } catch (Throwable ex) {
                   throw new RuntimeException();
            }
            return null;
        });
    }
}
