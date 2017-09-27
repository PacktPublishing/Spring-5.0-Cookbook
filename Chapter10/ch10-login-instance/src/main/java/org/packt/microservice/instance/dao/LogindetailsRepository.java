package org.packt.microservice.instance.dao;

import java.util.List;

import org.packt.microservice.instance.model.data.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogindetailsRepository extends JpaRepository<LoginDetails, Integer>{
	
	
	List<LoginDetails> findByUsername(String username);
	

}
