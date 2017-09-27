package org.packt.reactive.codes.service.impl;

import java.time.Duration;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;

import org.packt.reactive.codes.service.EmployeeNativeStreamService;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeNativeStreamServiceImpl implements EmployeeNativeStreamService {
	
	@Override
	public Mono<String> processFormUser(String name) {
		Function<String,String> upper = (str) -> str.toUpperCase();
		Predicate<String> longName = (str) -> str.length() > 5;
		Consumer<String> success = (str) -> System.out.println("successfully processed: " + str);
		Consumer<Throwable> error = (e) -> System.out.println("encountered an error: : " + e.getMessage());
		Consumer<String> onNext = (s) -> System.out.println("approved: " + s);
		Mono<String> makeoverName = Mono.just(name)
				.filter(longName)
				.map(upper)
				.doOnSuccess(success)
				.doOnError(error)
				.doOnNext(onNext)
				.onErrorReturn("invalid Name");
		return makeoverName;
	}

	@Override
	public Flux<String> getFormUsers(String... names) {
		Function<String,String> upper = (str) -> str.concat("---VALID USER");
		Comparator<String> ascSort = (str1, str2) -> str1.compareTo(str2);
		Runnable complete = () -> {
			System.out.println("completed processing");
		};
		Runnable terminate = () -> {
			System.out.println("terminated due to some problems");
		};
		Consumer<String> onNext = (s) -> System.out.println("validated: " + s);
		Flux<String> userNames = Flux.just(names)
				.map(upper)
				.sort(ascSort)
				.defaultIfEmpty("empty list")
				.doOnNext(onNext)
				.doOnComplete(complete)
				.doOnTerminate(terminate)
				.doOnError(Exception.class, (e) -> System.out.println("exits gracefully"));
		return userNames;
	}

	@Override
	public Flux<Integer> getAllAge(Integer age[]) {
		
		Function<Integer, Integer> addBufferAge = (a) -> a + 10/0;
		Flux<Integer> allAges = Flux
				.just(age)
				.map(addBufferAge)
				.doOnSubscribe(subscription ->
			        {
			            subscription.request(2);
			            System.out.println(subscription);
			     })
				.retryWhen(opionFlux -> Flux.range(1,5)
		                .flatMap(i ->  Flux.just(i).delayElements(Duration.ofMillis(10))))
			    .log("Adding 10", Level.INFO);	
		return allAges;
	}

}
