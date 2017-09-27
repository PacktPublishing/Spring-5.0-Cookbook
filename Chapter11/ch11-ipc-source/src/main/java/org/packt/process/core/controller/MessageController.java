package org.packt.process.core.controller;

import org.packt.process.core.model.data.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {
	
	  @Autowired
	  private Source source;

	  @RequestMapping(method = RequestMethod.GET,  value = "/selectDept/{id}")
	  @ResponseBody
	  public String verifyEmployee(@PathVariable("id") String id) {
		  Message<String> result = MessageBuilder.withPayload(id).build();
		  source.output().send(result);
          return result.getPayload();
      }
	  
	  @RequestMapping(method = RequestMethod.GET,  value = "/addDept/{id}/{deptid}/{name}/")
	  @ResponseBody
	  public Department addEmployee(@PathVariable("id") Integer id, @PathVariable("deptid") Integer deptid, @PathVariable("name") String name) {
		  Department dept = new Department();
		  dept.setId(id);
		  dept.setDeptid(deptid);
		  dept.setName(name);
		  Message<Department> result = MessageBuilder.withPayload(dept).build();
		  source.output().send(result);
          return result.getPayload();
      }

}
