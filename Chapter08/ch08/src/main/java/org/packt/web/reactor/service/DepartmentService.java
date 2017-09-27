package org.packt.web.reactor.service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import org.packt.web.reactor.model.data.Department;
import org.packt.web.reactor.model.data.Employee;
import org.packt.web.reactor.model.form.DepartmentForm;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface DepartmentService {

	public CompletableFuture<List<Department>> readDepartments();
	public void addDepartment(DepartmentForm dept);
	public void removeDepartment(Integer deptId);	
	public void updateDepartment(DepartmentForm dept, Integer id);
	public Callable<Department> getDeptId(Integer id);
	
	public Observable<Department> getDeptsRx();
	public Single<Department> getDeptRx(int id);
	public Flowable<String> getDepttNamesRx();

}
