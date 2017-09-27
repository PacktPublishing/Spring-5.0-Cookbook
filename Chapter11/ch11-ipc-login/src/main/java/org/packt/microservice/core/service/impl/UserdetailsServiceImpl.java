package org.packt.microservice.core.service.impl;

import java.util.Date;
import java.util.List;

import org.packt.microservice.core.dao.UserdetailsRepository;
import org.packt.microservice.core.model.data.UserDetails;
import org.packt.microservice.core.service.UserdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserdetailsServiceImpl implements UserdetailsService {
	
	@Autowired
	private UserdetailsRepository userdetailsRepository;

	@Override
	public UserDetails findUserById(Integer id) {
		// TODO Auto-generated method stub
		return userdetailsRepository.findById(id).orElse(new UserDetails());
	}

	@Override
	public List<UserDetails> findUserByMobile(String mobile) {
		// TODO Auto-generated method stub
		return userdetailsRepository.findByMobile(mobile);
	}

	@Override
	public List<UserDetails> findUserByFirstname(String firstname) {
		// TODO Auto-generated method stub
		return userdetailsRepository.findByFirstname(firstname);
	}

	@Override
	public List<UserDetails> findUserByLastname(String lastname) {
		// TODO Auto-generated method stub
		return userdetailsRepository.findByLastname(lastname);
	}

	@Override
	public List<UserDetails> findUserByMidname(String midname) {
		// TODO Auto-generated method stub
		return userdetailsRepository.findByMidname(midname);
	}

	@Override
	public List<UserDetails> findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userdetailsRepository.findByUsername(username);
	}

	@Override
	public List<UserDetails> findUserByBirthdate(Date birthdate) {
		// TODO Auto-generated method stub
		return userdetailsRepository.findByBirthdate(birthdate);
	}

	@Override
	public List<UserDetails> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userdetailsRepository.findByEmail(email);
	}

	@Override
	public List<UserDetails> findUserByStartdate(Date startdate) {
		// TODO Auto-generated method stub
		return userdetailsRepository.findByStartdate(startdate);
	}

	@Override
	public List<UserDetails> findAllUserdetails() {
		// TODO Auto-generated method stub
		return userdetailsRepository.findAll();
	}

	@Override
	public void saveUserdetails(UserDetails userdetails) {
		userdetailsRepository.saveAndFlush(userdetails);
		
	}

}
