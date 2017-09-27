package org.packt.reactive.codes.service.impl;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.packt.reactive.codes.dao.EmployeeDao;
import org.packt.reactive.codes.model.data.Employee;
import org.packt.reactive.codes.service.EmployeeTransformDataStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class EmployeeTransformDataStreamImpl implements EmployeeTransformDataStream {
	
	@Autowired
	private EmployeeDao employeeDaoImpl;

	@Override
	public Flux<String> mergeWithNames(List<String> others) {
		 Function<Employee, String> names = (emp) -> emp.getFirstName() + "---validated";
		 Function<Employee, Mono<String>> flatMapName = (emp) -> Mono.just(emp).map(names);
		 Flux<String> mergedNames =  Flux.fromIterable(employeeDaoImpl.getEmployees())
	        		.flatMap(flatMapName)
	                .mergeWith(Flux.fromIterable(others)
	                		.map(String::toUpperCase)
	                		.distinct()
	       				    .sort((s1, s2) -> s1.compareTo(s2)));
		return mergedNames;
	}

	@Override
	public Flux<String> concatWithNames(List<String> others) {
		Function<Employee, String> names = (emp) -> emp.getFirstName() + "---validated";
		Function<Employee, Mono<String>> flatMapName = (emp) -> Mono.just(emp).map(names);
		Flux<String> concatNames =  Flux.fromIterable(employeeDaoImpl.getEmployees())
	       .flatMap(flatMapName)
	       .concatWith(Flux.fromIterable(others))
	       					.map(String::toUpperCase)
	       				    .sort((s1, s2) -> s1.compareTo(s2));
       
		return concatNames;
	}

	@Override
	public Flux<Tuple2<String,String>> zipWithNames(List<String> others) {
		Function<Employee, String> names = (emp) -> emp.getFirstName() + "---validated";
		Function<Employee, Mono<String>> flatMapName = (emp) -> Mono.just(emp).map(names);
		Flux<Tuple2<String,String>> zipNames =  Flux.fromIterable(employeeDaoImpl.getEmployees())
	       .flatMap(flatMapName)
	       .zipWith(Flux.fromIterable(others));
       
		return zipNames;
	}

	@Override
	public Flux<String> flatMapWithNames(List<String> others) {
		Flux<String> flatMaps = Flux.fromIterable(others)
                .flatMap((str) ->{
                	return Mono.just(str).repeat(3).map(String::toUpperCase).delayElements(Duration.ofMillis(1));
                });
		return flatMaps;
	}

	@Override
	public Mono<Integer> countEmpRecReduce() {
		Function<Employee, Integer> ages = (emp) -> emp.getAge();
		Function<Employee, Mono<Integer>> flatMapAge = (emp) -> Mono.just(emp).map(ages);
		Mono<Integer> count = Flux.fromIterable(employeeDaoImpl.getEmployees())
				.flatMap(flatMapAge)
				.reduce((total, increment) -> total + increment);
		return count;
	}

	@Override
	public Flux<GroupedFlux<String, String>> groupNames() {
		Function<Employee, String> names = (emp) -> emp.getFirstName().toLowerCase();
		Flux<GroupedFlux<String, String>> grpsNames = Flux.fromIterable(employeeDaoImpl.getEmployees())
				.map(names)
				.groupBy(key -> key.charAt(0)+"");
		return grpsNames;
	}

	@Override
	public Flux<String> chooseEmission(List<String> others) {
		Function<Employee, String> names = (emp) -> emp.getFirstName();
		 Flux<String> sideA = Flux.fromIterable(others)
				 				.delayElements(Duration.ofMillis(200));
         Flux<String> sideB = Flux.fromIterable(employeeDaoImpl.getEmployees())
        		 				 .map(names)
        		 				.delayElements(Duration.ofMillis(300));
         Flux<String> sideC = Flux.fromIterable(others)
	 				.take(2);

         Flux<String> chosen = Flux.firstEmitting(sideA, sideB, sideC);

		return chosen;
	}

	@Override
	public String blockedStreamData() {
		Function<Employee, String> names = (emp) -> emp.getFirstName();
		String blockStringVal = Flux.fromIterable(employeeDaoImpl.getEmployees()).map(names).blockFirst();
		return blockStringVal;
	}

	@Override
	public Iterable<String> iterableData() {
		Function<Employee, String> names = (emp) -> emp.getFirstName();
		Iterable<String> namesIterate = Flux.fromIterable(employeeDaoImpl.getEmployees()).map(names).toIterable();
	
		return namesIterate;
	}

}
