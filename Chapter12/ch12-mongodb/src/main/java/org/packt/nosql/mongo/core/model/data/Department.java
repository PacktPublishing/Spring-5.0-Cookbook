package org.packt.nosql.mongo.core.model.data;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="department")
public class Department {
	
	private BigInteger _id;
	
	@Id
	private Long id;
	private Long deptid;
	private String name;
	
	@PersistenceConstructor
	public Department(BigInteger _id, Long id, Long deptid, String name) {
		super();
		this._id = _id;
		this.id = id;
		this.deptid = deptid;
		this.name = name;
	}

	public BigInteger get_id() {
		return _id;
	}

	public void set_id(BigInteger _id) {
		this._id = _id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeptid() {
		return deptid;
	}

	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	

}
