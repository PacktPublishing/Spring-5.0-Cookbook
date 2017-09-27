package org.packt.nosql.couch.core.model.data;

public class Department {
	
	private Long id;
	private Long deptid;
	private String name;

	public Department(Long id, Long deptid, String name) {
		this.id = id;
		this.deptid = deptid;
		this.name = name;
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
