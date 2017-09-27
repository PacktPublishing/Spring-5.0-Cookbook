package org.packt.starter.ioc.model;

import org.springframework.stereotype.Component;

@Component
public class DataService {
	
	public String getTitle(){
		return "Spring 5.0 Cookbook";
	}

}
