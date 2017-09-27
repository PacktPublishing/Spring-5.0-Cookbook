package org.packt.spring.boot.service.impl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import org.packt.spring.boot.dao.DepartmentDao;
import org.packt.spring.boot.model.data.Department;
import org.packt.spring.boot.model.form.DepartmentForm;
import org.packt.spring.boot.service.DepartmentService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import reactor.core.publisher.Mono;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDaoImpl;

	@Override
	public CompletableFuture<List<Department>> readDepartments() {
		try {
			System.out.println("readDepartments CompletableFuture login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture(departmentDaoImpl.getDepartments());
	}

	@Async
	@Override
	public void addDepartment(DepartmentForm dept) {
		System.out.println("addDepartment @Async login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Department deptData = new Department();
		deptData.setDeptId(dept.getDeptId());
		deptData.setName(dept.getName());
		departmentDaoImpl.addDepartmentByJT(deptData);
	}
	
	@Async
	@Override
	public void removeDepartment(Integer deptId) {
		departmentDaoImpl.delDepartment(deptId);
	}

	@Async
	@Override
	public void updateDepartment(DepartmentForm dept, Integer id) {
		System.out.println("updateDepartment @Async login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Department deptData = new Department();
		deptData.setDeptId(dept.getDeptId());
		deptData.setName(dept.getName());
		deptData.setId(id);
		departmentDaoImpl.updateDepartment(deptData);
	}

	@Override
	public Callable<Department> getDeptId(Integer id) {
		Callable<Department> task = new Callable<Department>() {
            @Override
            public  Department  call () throws Exception {
            	System.out.println("getDeptId Callable login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            	System.out.println("controller:readEmployeesCall task executor: " + Thread.currentThread().getName());
                Thread.sleep(5000);
                Department dept = departmentDaoImpl.getDepartmentData(id);
                return dept;
            }
        };
		return task;
	}
	
	@Override
	public Observable<Department> getDeptsRx() {
		Observable<Department> depts= Observable.fromIterable(departmentDaoImpl.getDepartments());
		return depts;
	}

	@Override
	public Single<Department> getDeptRx(int id) {
		Callable<Department> task = () -> departmentDaoImpl.getDepartmentData(id);
		Single<Department> dept = Single.fromCallable(task);
		return dept;
	}

	@Override
	public Flowable<String> getDepttNamesRx() {
		Function<Department, Publisher<String>> firstNames = (emp) -> {
			System.out.println("flux:function task executor login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return Mono.just(emp.getName()).map(String::toUpperCase);
		};
		Flowable<String> emps = Flowable.fromIterable(departmentDaoImpl.getDepartments()).flatMap(firstNames);
		return emps;
	}


}
