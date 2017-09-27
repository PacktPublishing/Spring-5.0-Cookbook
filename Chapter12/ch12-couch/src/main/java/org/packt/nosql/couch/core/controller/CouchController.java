package org.packt.nosql.couch.core.controller;

import java.io.IOException;
import java.util.List;

import org.packt.nosql.couch.core.model.data.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;

@RestController
public class CouchController {
	
	@Autowired
	private Database hrsDb;
	
	@Autowired
	private CloudantClient cloudant;
	
	@GetMapping("/alldbs")
	public List<String> checkdbs(){
		return cloudant.getAllDbs();
	}
	
	@GetMapping("/listDepts")
	public List<Department> listDepts() throws IOException{
		return hrsDb.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Department.class);
	}
	
	@GetMapping("/save/{id}/{deptid}/{name}")
	public String saver(@PathVariable("id") Long id, @PathVariable("deptid") Long deptid, @PathVariable("name") String name) {
		Response resp = hrsDb.save(new Department(id, deptid, name));
		return resp.getReason();
	}

}
