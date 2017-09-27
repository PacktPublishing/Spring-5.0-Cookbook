package org.packt.process.core.model.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="employee")
public class Permanent {
	
	private Integer id;
	private Integer deptid;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDeptid() {
		return deptid;
	}
	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
