package org.packt.microservice.core.dao;

import org.packt.microservice.core.model.data.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.lang.Integer;
import java.util.List;
import java.lang.String;

@Repository
public interface LogindetailsRepository extends JpaRepository<LoginDetails, Integer>{
	
	
	List<LoginDetails> findByUsername(String username);
	

}
