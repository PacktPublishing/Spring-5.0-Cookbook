package org.packt.microservice.instance.dao;

import java.util.Date;
import java.util.List;

import org.packt.microservice.instance.model.data.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserdetailsRepository extends JpaRepository<UserDetails, Integer>{
	
	List<UserDetails> findByMobile(String mobile);
	List<UserDetails> findByFirstname(String firstname);
	List<UserDetails> findByLastname(String lastname);
	List<UserDetails> findByMidname(String midname);
	List<UserDetails> findByUsername(String username);
	List<UserDetails> findByBirthdate(Date birthdate);
	List<UserDetails> findByEmail(String email);
	List<UserDetails> findByStartdate(Date startdate);
}
