package org.packt.reactive.core.test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.packt.reactive.codes.config.SpringDbConfig;
import org.packt.reactive.codes.dispatch.SpringDispatcherConfig;
import org.packt.reactive.codes.service.EmployeeNativeStreamService;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import reactor.core.publisher.Flux;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringDbConfig
		.class, SpringDispatcherConfig.class })
public class TestEmployeeNativeStreamService {
	
	@Autowired
	private EmployeeNativeStreamService employeeNativeStreamServiceImpl;
	
	@Test
	public void testFluxAgeArray(){
		List<Integer> bufferedAge = new ArrayList<>();
		employeeNativeStreamServiceImpl.getAllAge(new Integer[]{}).subscribe(bufferedAge::add);
		for(Integer age: bufferedAge){
			System.out.println(age);
		}
	}
	
	@Test
	public void testFluxEmpNames(){
		List<String> bufferedAge = new ArrayList<>();
		Consumer<String> convertList = (val) -> bufferedAge.add(val);
		employeeNativeStreamServiceImpl.getFormUsers(new String[]{"Anna", "Lorna"}).subscribe(convertList);
		for(String age: bufferedAge){
			System.out.println(age);
		}
	}
	
	@Test
	public void testMonoUserA(){
		Consumer<String> convertUser = (str) -> System.out.println("String object: " + str);;
		employeeNativeStreamServiceImpl.processFormUser("sjctrags").subscribe(convertUser);
		
	}
	
	@Test
	public void testMonoUserB(){
		employeeNativeStreamServiceImpl.processFormUser("sjct").subscribe(System.out::println);
		
	}
	
	@Test
	public void testMonoUserC(){
		Subscriber<String> subscriber = new Subscriber<String>(){

			@Override
			public void onComplete() {
				System.out.println("Mono streams ended successfully.");				
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("Something wrong happened. Exits now.");		
			}

			@Override
			public void onNext(String name) {
				System.out.println("String object: " + name);
				
			}

			@Override
			public void onSubscribe(Subscription subs) {
				subs.request(Long.MAX_VALUE);
			}
		};
		employeeNativeStreamServiceImpl.processFormUser("sjctrags").subscribe(subscriber);
		
	}
	
	@Test
	public void test(){
		Flux.just("Ben", "Michael", "Mark")
        .doOnNext(v -> {
            if (new Random().nextInt(10) + 1 == 5) {
                throw new RuntimeException("Boo!");
            }
        })
        .doOnSubscribe(subscription ->
        {
            System.out.println(subscription);
        })
        .retryWhen(throwableFlux -> Flux.range(1, 5)
                .flatMap(i -> {
                    System.out.println(i);
                    return Flux.just(i)
                            .delay(Duration.of(i, ChronoUnit.SECONDS));
                }))
        .subscribe(System.out::println);
	}

}
