package org.packt.reactive.codes.service.impl;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.packt.reactive.codes.dao.EmployeeDao;
import org.packt.reactive.codes.model.data.Employee;
import org.packt.reactive.codes.service.EmployeeScheduledStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Service
public class EmployeeScheduledStreamServiceImpl implements EmployeeScheduledStreamService {
	
	@Autowired
	private EmployeeDao employeeDaoImpl;

	@Override
	public Flux<Employee> elasticFlow() {
		
		Scheduler elastic = Schedulers.newElastic("elastic-runner");
		Predicate<Employee> validAge = (e) -> {
			System.out.println("filter thread " +Thread.currentThread().getName());
			return e.getAge() > 25;
		};
		Supplier<Flux<Employee>> deferredTask = ()->{
			System.out.println("defer thread "+Thread.currentThread().getName());
			return Flux.fromIterable(employeeDaoImpl.getEmployees());
		};
		Flux<Employee> deferred = Flux.defer(deferredTask).filter(validAge).subscribeOn(elastic);
		return deferred;
	}

	

	@Override
	public Flux<Employee> createPublisherThread() {
		Scheduler pubWorker = Schedulers.newSingle("pub-thread");
		Predicate<Employee> validAge = (e) -> {
			System.out.println("filter thread " +Thread.currentThread().getName());
			return e.getAge() > 25;
		};
		Supplier<Flux<Employee>> deferredTask = ()->{
			System.out.println("defer thread "+Thread.currentThread().getName());
			return Flux.fromIterable(employeeDaoImpl.getEmployees());
		};
		Flux<Employee> deferred = Flux.defer(deferredTask).filter(validAge).publishOn(pubWorker);
		return deferred;
	}

	@Override
	public Flux<Employee> createSubscriberThread() {
		Scheduler subWorker = Schedulers.newSingle("sub-thread");
		Predicate<Employee> validAge = (e) -> {
			System.out.println("filter thread " +Thread.currentThread().getName());
			return e.getAge() > 25;
		};
		Supplier<Flux<Employee>> deferredTask = ()->{
			System.out.println("defer thread "+Thread.currentThread().getName());
			return Flux.fromIterable(employeeDaoImpl.getEmployees());
		};
		Flux<Employee> deferred = Flux.defer(deferredTask).filter(validAge).subscribeOn(subWorker);
		return deferred;
	}

	@Override
	public Flux<Employee> createBothThreads() {
		Scheduler subWorker = Schedulers.newSingle("sub-thread");
		Scheduler pubWorker = Schedulers.newSingle("pub-thread");
		Predicate<Employee> validAge = (e) -> {
			System.out.println("filter thread " +Thread.currentThread().getName());
			return e.getAge() > 25;
		};
		Supplier<Flux<Employee>> deferredTask = ()->{
			System.out.println("defer thread "+Thread.currentThread().getName());
			return Flux.fromIterable(employeeDaoImpl.getEmployees());
		};
		Flux<Employee> deferred = Flux.defer(deferredTask).filter(validAge).subscribeOn(subWorker).publishOn(pubWorker);
		return deferred;
	}

	
	@Override
	public Flux<Employee> createPubAndMain() {
		Scheduler pubWorker = Schedulers.newSingle("pub-thread");
		Predicate<Employee> validAge = (e) -> {
			System.out.println("filter thread " +Thread.currentThread().getName());
			return e.getAge() > 25;
		};
		Supplier<Flux<Employee>> deferredTask = ()->{
			System.out.println("defer thread "+Thread.currentThread().getName());
			return Flux.fromIterable(employeeDaoImpl.getEmployees());
		};
		Flux<Employee> deferred = Flux.defer(deferredTask).publishOn(pubWorker).filter(validAge);
		return deferred;
	}

	@Override
	public Flux<String> createSchedGroupPub() {
		Scheduler subWorker = Schedulers.newSingle("sub-thread");
		Scheduler parallelGrp = Schedulers.newParallel("pub-grp", 8);
		Function<Employee, String> allCapsNames = (emp) -> emp.getFirstName().toUpperCase() + " " + emp.getLastName().toUpperCase();
		
		Flux<String> grpFlux = Flux.fromIterable(employeeDaoImpl.getEmployees())
			.publishOn(parallelGrp)
			.flatMap((emp)->{
				System.out.println("flatMap thread: " + Thread.currentThread().getName());
				return Mono.just(emp).map(allCapsNames).subscribeOn(subWorker);
			});
		return grpFlux;
	}

	@Override
	public Flux<String> createSchedGroupSub() {
		Scheduler pubWorker = Schedulers.newSingle("pub-thread");
		Scheduler parallelGrp = Schedulers.newParallel("sub-grp", 8);
		Function<Employee, String> allCapsNames = (emp) -> emp.getFirstName().toUpperCase() + " " + emp.getLastName().toUpperCase();
		
		Flux<String> strFlux = Flux.fromIterable(employeeDaoImpl.getEmployees())
				.publishOn(pubWorker)
				.flatMap((str)->{
					System.out.println("flatMap thread: " + Thread.currentThread().getName());
					return Mono.just(str).map(allCapsNames).subscribeOn(parallelGrp);
				});
		return strFlux;
	}



	@Override
	public Flux<String> selectNamesScheduler() {
		Scheduler winWorker = Schedulers.newSingle("window-thread");
		Function<Employee, String> allCapsNames = (emp) -> emp.getFirstName().toUpperCase() + " " + emp.getLastName().toUpperCase();
		Flux<String> convertWindows = Flux.fromIterable(employeeDaoImpl.getEmployees())
                .windowTimeout(2,Duration.ofMillis(20), winWorker)
                .flatMap(str -> str
                        .map(allCapsNames)
                        .collectList()
                        .map(name -> StringUtils.collectionToCommaDelimitedString(name))
                );
		return convertWindows;
	}
	
	

}
