package org.packt.process.core.config;

import org.packt.process.core.model.data.Department;
import org.packt.process.core.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class VerifyEmployeeService {
	
	@Autowired
    private DepartmentService departmentServiceImpl;
		
	private static final Logger log = LoggerFactory.getLogger(VerifyEmployeeService.class);
	
    @ServiceActivator(inputChannel=Sink.INPUT)
    public void validateEmployee(Integer deptId) {
    	Department dept = null;
    	try{
    		dept = departmentServiceImpl.findDeptByid(deptId);
    	}catch(Exception e){
    		dept = new Department();
    		dept.setName("Non-existent");
    	}
    	 
           log.info("{}", dept.getName());
    }
    
    @ServiceActivator(inputChannel=Sink.INPUT)
    public void addDepartment(Department dept) {
    	
    	try{
    		departmentServiceImpl.saveDeptRec(dept);
    	}catch(Exception e){
    	    log.info("{}", e.getMessage());
    	}
    	 
        log.info("{}", dept.getName());
    }
}
