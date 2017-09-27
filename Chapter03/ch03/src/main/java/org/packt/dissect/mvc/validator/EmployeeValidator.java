package org.packt.dissect.mvc.validator;

import java.util.Date;

import org.packt.dissect.mvc.model.form.EmployeeForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class EmployeeValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.equals(EmployeeForm.class);
	}

	@Override
	public void validate(Object model, Errors errors) {
		EmployeeForm empForm = (EmployeeForm) model;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "empty.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "empty.lastName");
		
		if(empForm.getAge() < 0) errors.rejectValue("age", "negative.age");
		if(empForm.getAge() > 65) errors.rejectValue("age", "retirable.age");
		
		if(empForm.getBirthday().before(new Date(50,0,1))) errors.rejectValue("birthday", "old.birthday");
		Date now = new Date();
		if(empForm.getBirthday().getYear() == now.getYear() || empForm.getBirthday().before(new Date(99,0,1))) errors.rejectValue("birthday", "underage.birthday");
	}
	
	

}
