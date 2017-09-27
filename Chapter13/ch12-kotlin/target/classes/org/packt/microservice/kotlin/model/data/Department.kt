package org.packt.microservice.kotlin.model.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Department {
	
	lateinit var id : Integer
	lateinit var name: String
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	lateinit var deptid: Integer
	
	 constructor(
       id: Integer, 
       name: String, 
       deptid: Integer) {
          this.id = id
          this.name = name
          this.deptid = deptid
    }
}