package org.packt.aop.transaction.core;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.packt.aop.transaction.model.data.Employee;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ManageNullsDao {

	private Logger logger = Logger.getLogger(ManageNullsDao.class);

	@Around("execution(* org.packt.aop.transaction.dao.impl.EmployeeDaoImpl.getEmployees(..))")
	public Object safeDaoEmps(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("ManageNullsDao.safeDaoEmps() detected : " + joinPoint.getSignature().getName());
		Object emps = joinPoint.proceed();
		if (emps == null) {
			return new ArrayList();
		} else {
			return emps;
		}
	}

	@Around("execution(* org.packt.aop.transaction.dao.impl.EmployeeDaoImpl.getEmployee(..))")
	public Object safeDaoOneEmp(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("ManageNullsDao.safeDaoOneEmp() detected : " + joinPoint.getSignature().getName());
		Object emp = joinPoint.proceed();
		if (emp == null) {
			return new Employee();
		} else {
			return emp;
		}
	}
}
