package org.packt.reactive.codes.service;

import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface EmployeeTransformDataStream {
	public Flux<String> mergeWithNames(List<String> others);
	public Flux<String> concatWithNames(List<String> others);
	public Flux<Tuple2<String,String>> zipWithNames(List<String> others);
	public Flux<String> flatMapWithNames(List<String> others);
	public Mono<Integer> countEmpRecReduce();
	public Flux<GroupedFlux<String, String>> groupNames();
	public Flux<String> chooseEmission(List<String> others);
	public String blockedStreamData();
	public Iterable<String> iterableData();
}
