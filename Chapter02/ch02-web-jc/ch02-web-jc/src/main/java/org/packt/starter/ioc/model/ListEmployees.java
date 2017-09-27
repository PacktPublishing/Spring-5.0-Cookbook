package org.packt.starter.ioc.model;

import java.util.List;

public class ListEmployees {
	
	private List<Employee> listEmps;
	private List<String> listEmpNames;
	

	public List<Employee> getListEmps() {
		return listEmps;
	}

	public void setListEmps(List<Employee> listEmps) {
		this.listEmps = listEmps;
	}

	public List<String> getListEmpNames() {
		return listEmpNames;
	}

	public void setListEmpNames(List<String> listEmpNames) {
		this.listEmpNames = listEmpNames;
	}
	
	

}
