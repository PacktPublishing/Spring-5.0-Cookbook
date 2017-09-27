package org.packt.nosql.mongo.core.model.data;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="employee")
public class Employee  {
	
	private BigInteger _id;
	
	@Id
	private Long id;
	
	private Long empid;
	private String firstname;
	private String lastname;
	private Long age;
	private String email;
	private Date birthday;
	private Long deptid;
	
	
	@PersistenceConstructor
	public Employee(Long id, BigInteger _id, Long empid, String firstname, String lastname, Long age, String email,
			Date birthday, Long deptid) {
		super();
		this.id = id;
		this._id = _id;
		this.empid = empid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.email = email;
		this.birthday = birthday;
		this.deptid = deptid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigInteger get_id() {
		return _id;
	}
	public void set_id(BigInteger _id) {
		this._id = _id;
	}
	public Long getEmpid() {
		return empid;
	}
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Long getDeptid() {
		return deptid;
	}
	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}
	
	
	
	
	
}
