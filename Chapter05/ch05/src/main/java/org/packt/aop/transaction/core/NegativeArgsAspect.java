package org.packt.aop.transaction.core;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.packt.aop.transaction.annotation.NegativeArgs;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NegativeArgsAspect {
	
	private Logger logger = Logger.getLogger(NegativeArgsAspect.class);
	
	@Pointcut("execution(* *(@org.packt.aop.transaction.annotation.NegativeArgs (*), ..))")
	protected void myPointcut() {
	}
	
	@AfterThrowing(pointcut = "myPointcut() && args(empId)", throwing = "e")
	public void afterThrowingException(JoinPoint joinPoint, Exception e, Integer empId) {
	    if(empId < 0){
	    	logger.info("cannot be negative number");
	    }
	}

	@AfterReturning(pointcut = "myPointcut() && args(empId)")
	public void afterSuccessfulReturn(JoinPoint joinPoint, Integer empId) {
		 if(empId < 0){
			 logger.info("cannot be negative number");
		    }
	}
}
