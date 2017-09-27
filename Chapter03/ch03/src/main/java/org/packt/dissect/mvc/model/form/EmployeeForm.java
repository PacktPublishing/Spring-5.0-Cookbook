package org.packt.dissect.mvc.model.form;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class EmployeeForm {
	
	@Size(min=2, max=30) 
	private String firstName;
	
	@Size(min=2, max=30) 
	private String lastName;
	
	@NotNull
	@Size(min=5, max=100) 
	private String position;
	
	@NotNull 
	@Min(0) @Max(100)
	private Integer age;
	
	@NotNull 
	@Past
	private Date birthday;
	
	
	@Email(message="Must be email formatted.")
	@NotEmpty(message="{email.empty}") 
	private String email;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	// Added Information 
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	

}
