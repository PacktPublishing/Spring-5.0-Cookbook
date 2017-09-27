package org.packt.process.core.processor;

import org.packt.process.core.model.data.Department;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

public class DeptIDValidProcesor extends ValidatingItemProcessor<Department> {
    public DeptIDValidProcesor() {
        super(
            item -> {
                if (item.getDeptid() < 400) {
                    throw new ValidationException("Customer ID lower than 400...");
                }
            }
        );
        setFilter(true);
    }
}