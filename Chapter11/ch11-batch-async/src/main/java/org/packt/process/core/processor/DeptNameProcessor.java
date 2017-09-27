package org.packt.process.core.processor;

import org.packt.process.core.model.data.Department;
import org.springframework.batch.item.ItemProcessor;

public class DeptNameProcessor implements ItemProcessor<Department, Department> {
    @Override
    public Department process(final Department item) throws Exception {
        if (item.getName().length() >= 5) {
            return item;
        }
        return null;
    }
}