package org.packt.aop.transaction.service.impl;

import java.util.List;

import org.packt.aop.transaction.annotation.MonitorService;
import org.packt.aop.transaction.annotation.NegativeArgs;
import org.packt.aop.transaction.dao.EmployeeDao;
import org.packt.aop.transaction.model.data.Employee;
import org.packt.aop.transaction.model.form.EmployeeForm;
import org.packt.aop.transaction.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDaoImpl;

	@Override
	public List<Employee> readEmployees() {
		return employeeDaoImpl.getEmployees();
	}

	@Override
	public void addEmployee(EmployeeForm empForm) {
		System.out.println("Adding Employee executed.");
		Employee emp = new Employee();
		emp.setDeptId(empForm.getEmpId());
		emp.setFirstName(empForm.getFirstName());
		emp.setLastName(empForm.getLastName());
		emp.setAge(empForm.getAge());
		emp.setBirthday(empForm.getBirthday());
		emp.setEmail(empForm.getEmail());
		emp.setDeptId(empForm.getDeptId());
		emp.setEmpId(empForm.getEmpId());
		employeeDaoImpl.addEmployeeBySJI(emp);

	}

	@MonitorService
	@Override
	public Employee readEmployee(@NegativeArgs Integer empId) {
		return employeeDaoImpl.getEmployee(empId);
	}

	@Override
	public void updateEmployee(EmployeeForm empForm, int id) {
		Employee emp = new Employee();
		emp.setDeptId(empForm.getEmpId());
		emp.setFirstName(empForm.getFirstName());
		emp.setLastName(empForm.getLastName());
		emp.setAge(empForm.getAge());
		emp.setBirthday(empForm.getBirthday());
		emp.setEmail(empForm.getEmail());
		emp.setDeptId(empForm.getDeptId());
		emp.setId(id);
		
		employeeDaoImpl.updateEmployee(emp);

	}

	@Override
	public void delEmployee(Integer empId) {
		employeeDaoImpl.delEmployee(empId);
	}
}
