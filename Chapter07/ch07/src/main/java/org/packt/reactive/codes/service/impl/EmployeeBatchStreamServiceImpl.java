package org.packt.reactive.codes.service.impl;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.packt.reactive.codes.dao.EmployeeDao;
import org.packt.reactive.codes.model.data.Employee;
import org.packt.reactive.codes.service.EmployeeBatchStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeBatchStreamServiceImpl implements EmployeeBatchStreamService {
	
	@Autowired
	private EmployeeDao employeeDaoImpl;

	@Override
	public Flux<Employee> getDeferredEmployees() {
		Predicate<Employee> validAge = (e) -> e.getAge() > 25;
		Supplier<Flux<Employee>> deferredTask = ()->Flux.fromIterable(employeeDaoImpl.getEmployees());
		Flux<Employee> deferred = Flux.defer(deferredTask).log().filter(validAge);
		return deferred;
	}

	@Override
	public Flux<Employee> selectSomeEmpRecords() {
		Flux<Employee> takeSomeRecs = Flux.fromIterable(employeeDaoImpl.getEmployees()).log().skip(2).take(Duration.ofMillis(4));
		return takeSomeRecs;
	}

	@Override
	public Mono<Employee> selectOneEmployee() {
		Runnable cancel = () ->{
			System.out.println("stream is cancelled");
		};
		Mono<Employee> oneRecord = Flux.fromIterable(employeeDaoImpl.getEmployees()).doOnCancel(cancel).log().take(1).singleOrEmpty();
		return oneRecord;
	}

	@Override
	public Flux<List<Employee>> getEmployeesByBatch() {
		Flux<List<Employee>> recordsByBatch = Flux.fromIterable(employeeDaoImpl.getEmployees()).buffer(2);
		return recordsByBatch;
	}

	@Override
	public Flux<String> getTimedFirstNames() {
		Function<Employee, String> firstNames = (e) -> e.getFirstName();
		Supplier<Flux<String>> deferredTask = ()->Flux.fromIterable(employeeDaoImpl.getEmployees()).map(firstNames);
	    Flux<String> timedDefer = Flux.defer(deferredTask).log().timeout(Duration.ofMillis(320));
		return timedDefer;
	}
	
	@Override
	public Flux<Employee> selectEmpDelayed() {
	
		Supplier<Flux<Employee>> deferredTask = ()->Flux.fromIterable(employeeDaoImpl.getEmployees());
		Flux<Employee> oneRecord = Flux.defer(deferredTask).take(Duration.ofHours(1)).delayElements(Duration.ofSeconds(10));
		return oneRecord;
	}

}
