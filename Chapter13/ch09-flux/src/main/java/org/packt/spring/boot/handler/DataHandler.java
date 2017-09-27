package org.packt.spring.boot.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.stream.Stream;

import org.packt.spring.boot.model.data.Employee;
import org.packt.spring.boot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DataHandler {
	
	@Autowired
	private EmployeeService employeeServiceImpl;

	public Mono<ServerResponse> fluxHello(ServerRequest req) {
		return ok().body(Flux.just("Hello", "World!"), String.class);
	}

	public Mono<ServerResponse> stream(ServerRequest req) {
		Stream<String> streamData = Stream.of("i", "love", "reactive", "programming").sorted()
				.map((str) -> str.toUpperCase() + " ");
		Flux<String> flux = Flux.fromStream(streamData);
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, String.class);
	}
	
	
	public Mono<ServerResponse> empList(ServerRequest req) {
		Flux<Employee> flux = Flux.fromIterable(employeeServiceImpl.findAllEmps());
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, Employee.class);
	}
}