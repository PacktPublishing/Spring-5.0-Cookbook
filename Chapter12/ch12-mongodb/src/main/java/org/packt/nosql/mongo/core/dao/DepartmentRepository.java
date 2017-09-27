package org.packt.nosql.mongo.core.dao;

import java.math.BigInteger;

import org.packt.nosql.mongo.core.model.data.Department;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface DepartmentRepository extends ReactiveMongoRepository<Department, Long>{
	public Flux<Department> findAllById(Flux<Long> ids);
	public Flux<Department> findAllByName(String name);
}
