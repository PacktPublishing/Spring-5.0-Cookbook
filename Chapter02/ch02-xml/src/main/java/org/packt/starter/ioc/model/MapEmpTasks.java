package org.packt.starter.ioc.model;

import java.util.Map;

public class MapEmpTasks {
	private Map<String, Employee> mapEmpTask;
	private Map<String,String> mapEmpMgr;
	
	public Map<String, Employee> getMapEmpTask() {
		return mapEmpTask;
	}
	public void setMapEmpTask(Map<String, Employee> mapEmpTask) {
		this.mapEmpTask = mapEmpTask;
	}
	public Map<String, String> getMapEmpMgr() {
		return mapEmpMgr;
	}
	public void setMapEmpMgr(Map<String, String> mapEmpMgr) {
		this.mapEmpMgr = mapEmpMgr;
	}
	
	

}
