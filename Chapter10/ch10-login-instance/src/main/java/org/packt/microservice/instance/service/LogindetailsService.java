package org.packt.microservice.instance.service;

import java.util.List;

import org.packt.microservice.instance.model.data.LoginDetails;

public interface LogindetailsService {
	
	public List<LoginDetails> findLoginByUsername(String username);
	public LoginDetails  findLoginById(Integer id);
	public List<LoginDetails> findAllLogindetails();
	public void saveLogindetails(LoginDetails loginDetails);
	
}
