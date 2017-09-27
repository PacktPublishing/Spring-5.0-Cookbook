package org.packt.reactive.codes.service;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface SynchDataStream {
	
	public Publisher<String> getStreamPublisher();
	public Mono<String> getStreamMono();
	public Flux<Double> getListSalary();
	public Flux<String> getStringFlow();
	
	public void executeBatchProcess();
	
	

}
