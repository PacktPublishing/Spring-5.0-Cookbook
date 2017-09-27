package org.packt.microservice.core.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.stream.Stream;

import org.packt.microservice.core.model.data.CountDept;
import org.packt.microservice.core.model.data.Department;
import org.packt.microservice.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Component
public class DeptDataHandler {
	
	// Samples
	
	@Autowired
	private DepartmentService departmentServiceImpl;

	public Mono<ServerResponse> fluxHello(ServerRequest req) {
		return ok().body(Flux.just("Hello", "World!"), String.class);
	}

	public Mono<ServerResponse> stream(ServerRequest req) {
		Stream<String> streamData = Stream.of("i", "love", "reactive", "programming").sorted()
				.map((str) -> str.toUpperCase() + " ");
		Flux<String> flux = Flux.fromStream(streamData);
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, String.class);
	}
	
	// Department Handlers
	
    public Mono<ServerResponse> deptList(ServerRequest req) {
		Flux<Department> flux = Flux.fromIterable(departmentServiceImpl.findAllDepts());
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, Department.class);
	}
        
    public Mono<ServerResponse> chooseDeptById(ServerRequest req) {
		Scheduler subWorker = Schedulers.newSingle("sub-thread");
		Mono<Department> emp = Mono.defer(() -> Mono.justOrEmpty(departmentServiceImpl.findDeptByid(Integer.parseInt(req.pathVariable("id"))))).subscribeOn(subWorker);
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(emp, Department.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
    
    public Mono<ServerResponse> chooseFluxDepts(ServerRequest req) {
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(req.bodyToFlux(Integer.class).flatMap((id) -> Mono.justOrEmpty(departmentServiceImpl.findDeptByid(id))), Department.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
    
    public Mono<ServerResponse> saveDepartmentMono(ServerRequest req) {
    	Scheduler subWorker = Schedulers.newSingle("sub-thread");
    	Mono<Department> department = req.bodyToMono(Department.class).doOnNext(departmentServiceImpl::saveDeptRec).subscribeOn(subWorker);
       	return ok().contentType(MediaType.APPLICATION_STREAM_JSON).build(department.then());
 	}
    
    public Mono<ServerResponse> countDepts(ServerRequest req) {
		Mono<Long> count = Flux.fromIterable(departmentServiceImpl.findAllDepts())
				.count();	
		CountDept countDept = new CountDept();
		countDept.setCount(count.block());
		Mono<CountDept> monoCntDept = Mono.justOrEmpty(countDept);
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(monoCntDept, CountDept.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
}