package org.packt.functional.codes.service;

import java.util.List;

import org.packt.functional.codes.model.data.Employee;

@FunctionalInterface
public interface AgeLimitService {
    public List<Employee> qualifiedEmps();
}
