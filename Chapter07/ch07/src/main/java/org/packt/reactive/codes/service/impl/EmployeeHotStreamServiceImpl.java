package org.packt.reactive.codes.service.impl;

import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Function;

import org.packt.reactive.codes.dao.EmployeeDao;
import org.packt.reactive.codes.model.data.Employee;
import org.packt.reactive.codes.service.EmployeeHotStreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;
import reactor.core.publisher.ReplayProcessor;
import reactor.core.publisher.TopicProcessor;
import reactor.core.publisher.UnicastProcessor;
import reactor.core.publisher.WorkQueueProcessor;

@Service
public class EmployeeHotStreamServiceImpl implements EmployeeHotStreamService {
	
	@Autowired
	private EmployeeDao employeeDaoImpl;
	
	@Override
	public ConnectableFlux<String> freeFlowEmps() {
		 Vector<String> rosterNames = new Vector<>();
		 Function<Employee, String> familyNames = (emp) -> emp.getLastName().toUpperCase();
		 ConnectableFlux<String> flowyNames = Flux.fromIterable(employeeDaoImpl.getEmployees()).log().map(familyNames).cache().publish();
		// flowyNames.subscribe(System.out::println);
		 flowyNames.subscribe(rosterNames::add);
		 System.out.println(rosterNames);
		return flowyNames;
	}


	@Override
	public void monoProcessorGetEmployee(Integer id) {
		MonoProcessor<Integer> future = MonoProcessor.create();
		Consumer<Integer> checkEmp = (rowId) ->{
			if(employeeDaoImpl.getEmployee(rowId) == null){
				System.out.println("Employee with id: " + rowId + " does not exists.");
			}else{
				System.out.println("Employee with id: " + rowId + " exists.");
			}
		};
		
		Mono<Integer> engine = future
			    .doOnNext(checkEmp)
		     	.doOnSuccess(emp -> {
					System.out.println("Employee's age is " + employeeDaoImpl.getEmployee(emp).getAge());
					System.out.println("Employee's dept is: " + employeeDaoImpl.getEmployee(emp).getDeptId());
				})
		        .doOnTerminate((sup, ex) -> System.out.println("Transaction terminated with error: " +ex.getMessage()))
		        .doOnError(ex -> System.out.println("Error: " + ex.getMessage()));
		
		engine.subscribe(System.out::println);
		
		future.onNext(id);
		int valStream = future.block();
		System.out.println("Employee's ID again is: " + valStream);
	}

	@Override
	public void fluxProcessorGetEmployee(List<Integer> ids) {
		Function<Integer,Integer> checkEmp = (id) ->{
			if(!(employeeDaoImpl.getEmployee(id) == null)){
				return employeeDaoImpl.getEmployee(id).getAge();
			}else{
				return -1;
			}
		};
		 FluxProcessor<Integer, Integer> cpuFlow = EmitterProcessor.create();
		 Flux<Integer> fluxp = cpuFlow.map(checkEmp);
		 Flux<Integer> gradientNum = cpuFlow.map((num) -> num + 1000);
		
		 fluxp.subscribe(System.out::println);
		 gradientNum.subscribe(System.out::println);
		 
		 for(Integer id: ids){
			 cpuFlow.onNext(id);
		 }
		
		 cpuFlow.onComplete();
	}

	@Override
	public void validateNamesTopic(List<String> names) {
		TopicProcessor<String> topicProcessor = TopicProcessor.create();
		Function<String,String> appendLic = (name) -> name.concat(".112234");
		Function<String,String> appendKey = (name) -> name.concat("-AEK2345J");
		Function<String,String> upperCase = (name) -> name.toUpperCase();
		Flux<String> formatter1 = topicProcessor.filter((s) -> s.length() > 4).map(appendLic);
		Flux<String> formatter2 = topicProcessor.filter((s) -> s.startsWith("J")).map(appendKey);
		Flux<String> formatter3 = topicProcessor.filter((s) -> s.endsWith("win")).map(upperCase);

		formatter1.subscribe(System.out::println);
		formatter2.subscribe(System.out::println);
		formatter3.subscribe(System.out::println);

		for(String name : names){
			topicProcessor.onNext(name);
		}

		topicProcessor.onComplete();	
	}

	

	@Override
	public void validateNamesReplay(List<String> names) {
		ReplayProcessor<String> replayProcessor = ReplayProcessor.create();
		Function<String,String> appendLic = (name) -> name.concat(".112234");
		Function<String,String> appendKey = (name) -> name.concat("-AEK2345J");
		Function<String,String> upperCase = (name) -> name.toUpperCase();
		Flux<String> formatter1 = replayProcessor.filter((s) -> s.length() > 4).map(appendLic);
		Flux<String> formatter2 = replayProcessor.filter((s) -> s.startsWith("J")).map(appendKey);
		Flux<String> formatter3 = replayProcessor.filter((s) -> s.endsWith("win")).map(upperCase);

		formatter1.subscribe(System.out::println);
		formatter2.subscribe(System.out::println);
		formatter3.subscribe(System.out::println);

		for(String name : names){
			replayProcessor.onNext(name);
		}
		
		replayProcessor.onComplete();
	}


	@Override
	public void validateNamesWorkQueue(List<String> names) {
		WorkQueueProcessor<String> wqueueProcessor = WorkQueueProcessor.create();
		Function<String,String> appendLic = (name) -> name.concat(".112234");
		Function<String,String> appendKey = (name) -> name.concat("-AEK2345J");
		Function<String,String> upperCase = (name) -> name.toUpperCase();
		Flux<String> formatter1 = wqueueProcessor.filter((s) -> s.length() > 4).map(appendLic);
		Flux<String> formatter2 = wqueueProcessor.filter((s) -> s.startsWith("J")).map(appendKey);
		Flux<String> formatter3 = wqueueProcessor.filter((s) -> s.endsWith("win")).map(upperCase);

		formatter1.subscribe(System.out::println);
		formatter2.subscribe(System.out::println);
		formatter3.subscribe(System.out::println);

		for(String name : names){
			wqueueProcessor.onNext(name);
		}
		
		wqueueProcessor.onComplete();
	}


	@Override
	public void validateNamesUnicast(List<String> names) {
		UnicastProcessor<String> unicastProcessor = UnicastProcessor.create();
		Function<String,String> appendLic = (name) -> name.concat(".112234");
		Function<String,String> appendKey = (name) -> name.concat("-AEK2345J");
		Function<String,String> upperCase = (name) -> name.toUpperCase();
		
		Flux<String> formatter1 = unicastProcessor.filter((s) -> s.length() > 4).map(appendLic);
		// CANNOT RUN ANYMORE THE SUBSCRIBERS BELOW 
		//Flux<String> formatter2 = unicastProcessor.filter((s) -> s.startsWith("J")).map(appendKey);
		//Flux<String> formatter3 = unicastProcessor.filter((s) -> s.endsWith("win")).map(upperCase);

		formatter1.subscribe(System.out::println);
		for(String name : names){
			unicastProcessor.onNext(name);
		}
	
		unicastProcessor.onComplete();
	}

	

}
