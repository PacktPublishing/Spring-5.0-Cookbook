package org.packt.reactive.codes.service;

import org.packt.reactive.codes.model.data.Employee;

import reactor.core.publisher.Flux;

public interface EmployeeScheduledStreamService {
	
	public Flux<Employee> createPublisherThread();
	public Flux<Employee> createSubscriberThread();
	public Flux<Employee> createBothThreads();
	public Flux<Employee> createPubAndMain();
	public Flux<String> createSchedGroupPub();
	public Flux<String> createSchedGroupSub();
	public Flux<Employee> elasticFlow();
	public Flux<String> selectNamesScheduler();
}
