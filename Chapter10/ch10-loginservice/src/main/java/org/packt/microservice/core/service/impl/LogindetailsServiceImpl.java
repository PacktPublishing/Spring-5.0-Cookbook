package org.packt.microservice.core.service.impl;

import java.util.List;

import org.packt.microservice.core.dao.LogindetailsRepository;
import org.packt.microservice.core.model.data.LoginDetails;
import org.packt.microservice.core.service.LogindetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogindetailsServiceImpl implements LogindetailsService {
	
	@Autowired
	private LogindetailsRepository logindetailsRepository;

	@Override
	public List<LoginDetails> findLoginByUsername(String username) {
		// TODO Auto-generated method stub
		return logindetailsRepository.findByUsername(username);
	}

	@Override
	public LoginDetails findLoginById(Integer id) {
		// TODO Auto-generated method stub
		return logindetailsRepository.findById(id).orElse(new LoginDetails());
	}

	@Override
	public List<LoginDetails> findAllLogindetails() {
		// TODO Auto-generated method stub
		return logindetailsRepository.findAll();
	}

	@Override
	public void saveLogindetails(LoginDetails loginDetails) {
		logindetailsRepository.saveAndFlush(loginDetails);
		
	}

}
