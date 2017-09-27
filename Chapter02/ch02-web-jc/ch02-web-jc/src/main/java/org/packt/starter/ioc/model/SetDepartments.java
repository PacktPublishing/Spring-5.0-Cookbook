package org.packt.starter.ioc.model;

import java.util.Set;

public class SetDepartments {
	
	private Set<Department> setDepts;
	private Set<String> deptNames;
	
	public Set<Department> getSetDepts() {
		return setDepts;
	}

	public void setSetDepts(Set<Department> setDepts) {
		this.setDepts = setDepts;
	}

	public Set<String> getDeptNames() {
		return deptNames;
	}

	public void setDeptNames(Set<String> deptNames) {
		this.deptNames = deptNames;
	}
	
	

}
