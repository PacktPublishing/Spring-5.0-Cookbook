package org.packt.starter.ioc.model;

import java.util.Date;

public class Employee {
	
	private String firstName;
	private String lastName;
	private Date birthdate;
	private Integer age;
	private Double salary;
	private String position;
	private Department dept;
	
	public Employee(){
		System.out.println("an employee is created.");
	}

	public Employee(String firstName, String lastName, Date birthdate, Integer age, Double salary, String position,
			Department dept) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.age = age;
		this.salary = salary;
		this.position = position;
		this.dept = dept;
		System.out.println("an employee is created.");
	}

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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	

}
