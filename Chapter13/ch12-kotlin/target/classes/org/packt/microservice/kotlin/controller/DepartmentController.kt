package org.packt.microservice.kotlin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import org.packt.microservice.kotlin.dao.DepartmentRepository
import reactor.core.publisher.Flux;

@RestController
public class DepartmentController {
	
	 @Autowired
	  private lateinit var repository:DepartmentRepository

     @GetMapping("/listdepts")
     fun findAll() = repository.findAll()

     @GetMapping("/listdepts/{name}")
      fun findByName(@PathVariable name:String) = repository.findByName(name)
	
	 @GetMapping("/fluxdepts")
     fun fluxDepts() = Flux.just(repository.findAll())
}