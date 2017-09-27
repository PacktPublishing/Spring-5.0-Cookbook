package org.packt.nosql.mongo.core.dao;

import org.packt.nosql.mongo.core.model.data.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long>{
	
    public Flux<Employee> findAllById(Flux<Long> ids);
	public Flux<Employee> findAllByFirstname(String fname);
	public Flux<Employee> findAllByLastname(String lname);
}
