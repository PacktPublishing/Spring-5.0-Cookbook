package org.packt.reactive.codes.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.packt.reactive.codes.dao.EmployeeDao;
import org.packt.reactive.codes.model.data.Employee;
import org.packt.reactive.codes.service.EmployeeRxJavaService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import reactor.core.publisher.Mono;

@Service
public class EmployeeRxJavaServiceImpl implements EmployeeRxJavaService {
	
	@Autowired
	private EmployeeDao employeeDaoImpl;

	@Override
	public Observable<Employee> getEmployeesRx() {
		Observable<Employee> publishedEmployees= Observable.fromIterable(employeeDaoImpl.getEmployees());
		return publishedEmployees;
	}

	@Override
	public Single<Employee> getEmployeeRx(int empid) {
		Callable<Employee> task = () -> employeeDaoImpl.getEmployee(empid);
		Single<Employee> emp = Single.fromCallable(task);
		return emp;
	}

	@Override
	public Flowable<String> getFirstNamesRx() {
		Function<Employee, Publisher<String>> firstNames = (emp) -> Mono.just(emp.getFirstName()).map(String::toUpperCase);
		Flowable<String> emps = Flowable.fromIterable(employeeDaoImpl.getEmployees()).flatMap(firstNames);
		return emps;
	}

	@Override
	public Flowable<String> getEmpNamesRx() {
		Scheduler observerWorker = Schedulers.single();
		Scheduler subscriberWorker = Schedulers.newThread();
		Function<Employee, String> names = (emp) -> emp.getFirstName() + emp.getLastName();
		Flowable<String> emps = Flowable.fromIterable(employeeDaoImpl.getEmployees())
				.map(names).observeOn(observerWorker).subscribeOn(subscriberWorker);
		return emps;
	}

	@Override
	public Flowable<String> getEmpNamesParallelRx() {
		Function<Employee, String> names = (emp) ->{
			System.out.println("flatMap thread: " + Thread.currentThread().getName());
			return emp.getFirstName().charAt(0) + emp.getLastName();
		};
		
		Flowable<String> parallelEmpFlux = Flowable.fromIterable(employeeDaoImpl.getEmployees()).map(names)
              .subscribeOn(Schedulers.computation());
		return parallelEmpFlux;
	}

	@Override
	public Flowable<String> combinedStreamRx(List<String> others) {
		Function<Employee, String> names = (emp) -> emp.getFirstName() + "---validated";
		Flowable<String> zipNames = Flowable.fromIterable(employeeDaoImpl.getEmployees())
			.map(names)
	       .sorted()
	       .zipWith(others,(str1, str2) -> String.format("%s. %s", str1, str2));
       return zipNames;
	}

	@Override
	public ConnectableObservable<String> freeFlowEmps() {
		 List<String> rosterNames = new ArrayList<>();
		 Function<Employee, String> familyNames = (emp) -> emp.getLastName().toUpperCase();
		 ConnectableObservable<String> flowyNames = Observable.fromIterable(employeeDaoImpl.getEmployees()).map(familyNames).cache().publish();
		 
		 flowyNames.subscribe(System.out::println);
		 flowyNames.subscribe((name) ->{
			 rosterNames.add(name);
		 }); 
		 System.out.println(rosterNames);
		return flowyNames;
	}

}
