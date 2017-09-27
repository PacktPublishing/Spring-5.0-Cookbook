package org.packt.spring.boot.service;

import java.util.List;

import org.packt.spring.boot.model.data.Department;

public interface DepartmentService {
   public List<Department> getAllDepts();
}
