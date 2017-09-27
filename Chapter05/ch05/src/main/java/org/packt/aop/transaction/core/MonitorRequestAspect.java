package org.packt.aop.transaction.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.packt.aop.transaction.annotation.MonitorRequest;
import org.packt.aop.transaction.annotation.MonitorService;
import org.packt.aop.transaction.model.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@Aspect
public class MonitorRequestAspect {
	
	@Autowired
    private TransactionTemplate template;

    private Logger logger = Logger.getLogger(MonitorRequestAspect.class);

    @Around("execution(* *(..)) && @annotation(monitor)")
    public void logIt(ProceedingJoinPoint pjp, MonitorRequest monitor) {
    	String methodName = pjp.getSignature().getName();
    	template.execute(s->{
            try{
                logger.info("executing request handler: " + methodName);
            } catch (Throwable ex) {
                   throw new RuntimeException();
            }
            return null;
        });
    }
}
