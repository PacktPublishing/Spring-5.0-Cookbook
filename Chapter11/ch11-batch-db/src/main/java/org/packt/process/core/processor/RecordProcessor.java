package org.packt.process.core.processor;

import org.packt.process.core.model.data.Employee;
import org.packt.process.core.model.data.Permanent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class RecordProcessor implements ItemProcessor<Employee, Permanent> {

    private static final Logger log = LoggerFactory.getLogger(RecordProcessor.class);

    @Override
    public Permanent process(Employee item) throws Exception {
    	 if (item.getAge() >= 18) {
    		 Permanent perm = new Permanent();
    		 perm.setId(item.getId());
    		 perm.setDeptid(item.getDeptid());
    		 perm.setName(item.getFirstname() + " " +item.getLastname());
    		 log.info("empId " + perm.getId() + " passed." );
             return perm;
         }
         return null;
    }
}