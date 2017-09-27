package org.packt.microservice.core.dao;

import org.packt.microservice.core.model.data.LoginDetails;
import org.packt.microservice.core.model.data.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.lang.String;
import java.util.List;
import java.util.Date;
import java.lang.Integer;

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
