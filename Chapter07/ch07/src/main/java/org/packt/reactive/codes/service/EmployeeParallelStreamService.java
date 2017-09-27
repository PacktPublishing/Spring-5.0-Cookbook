package org.packt.reactive.codes.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;

public interface EmployeeParallelStreamService {
	
	public Flux<String> parallelEmployeeNames();
	public Flux<GroupedFlux<Integer, Integer>> parallelGrpAvg();
	public Flux<String> repeatExecs();
	
}
