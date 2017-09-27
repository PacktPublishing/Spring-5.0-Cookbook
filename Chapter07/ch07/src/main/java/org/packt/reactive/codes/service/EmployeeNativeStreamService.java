package org.packt.reactive.codes.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeNativeStreamService {
	
	public Mono<String> processFormUser(String name);
	public Flux<String> getFormUsers(String... names);
	public Flux<Integer> getAllAge(Integer[] age);

}
