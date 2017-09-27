package org.packt.microservice.core.service;

import java.util.Date;
import java.util.List;

import org.packt.microservice.core.model.data.UserDetails;

public interface UserdetailsService {
	
	public UserDetails findUserById(Integer id);
	public List<UserDetails> findAllUserdetails();
	public List<UserDetails> findUserByMobile(String mobile);
	public List<UserDetails> findUserByFirstname(String firstname);
	public List<UserDetails> findUserByLastname(String lastname);
	public List<UserDetails> findUserByMidname(String midname);
	public List<UserDetails> findUserByUsername(String username);
	public List<UserDetails> findUserByBirthdate(Date birthdate);
	public List<UserDetails> findUserByEmail(String email);
	public List<UserDetails> findUserByStartdate(Date startdate);
	public void saveUserdetails(UserDetails userdetails);

}
