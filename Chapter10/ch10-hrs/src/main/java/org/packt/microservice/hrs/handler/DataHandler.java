package org.packt.microservice.hrs.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

import org.packt.microservice.hrs.model.data.AveAge;
import org.packt.microservice.hrs.model.data.CountDept;
import org.packt.microservice.hrs.model.data.CountEmp;
import org.packt.microservice.hrs.model.data.Department;
import org.packt.microservice.hrs.model.data.Employee;
import org.packt.microservice.hrs.model.data.TotalAge;
import org.packt.microservice.hrs.service.DepartmentService;
import org.packt.microservice.hrs.service.EmployeeService;
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
public class DataHandler {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
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
	
	// Employee Handlers
	
	public Mono<ServerResponse> empList(ServerRequest req) {
		Flux<Employee> flux = Flux.fromIterable(employeeServiceImpl.findAllEmps());
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, Employee.class);
	}
	
	public Mono<ServerResponse> chooseEmpById(ServerRequest req) {
		Scheduler subWorker = Schedulers.newSingle("sub-thread");
		Mono<Employee> emp = Mono.defer(() -> Mono.justOrEmpty(employeeServiceImpl.findEmployeeByid(Integer.parseInt(req.pathVariable("id"))))).subscribeOn(subWorker);
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(emp, Employee.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> chooseFluxEmps(ServerRequest req) {
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(req.bodyToFlux(Integer.class).flatMap((id) -> Mono.justOrEmpty(employeeServiceImpl.findEmployeeByid(id))), Employee.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	
	public Mono<ServerResponse> chooseFluxEmpsValidAge(ServerRequest req) {
		Flux<Employee> flux = Flux.fromIterable(employeeServiceImpl.findAllEmps()).filter((emp)-> emp.getAge() > Integer.parseInt(req.pathVariable("age")));
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, Employee.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> countEmpsPerDept(ServerRequest req) {
		Function<Employee, Integer> ages = (emp) -> emp.getAge();
		Function<Employee, Mono<Integer>> flatMapAge = (emp) -> Mono.just(emp).map(ages);
		Mono<Integer> count = Flux.fromIterable(employeeServiceImpl.findAllEmps()).filter((emp) -> emp.getDeptid().equals(Integer.parseInt(req.pathVariable("deptid"))))
				.flatMap(flatMapAge)
				.reduce((total, increment) -> total + increment);
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(count, Integer.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> totalAge(ServerRequest req) {
		Function<Employee, Integer> ages = (emp) -> emp.getAge();
		Function<Employee, Mono<Integer>> flatMapAge = (emp) -> Mono.just(emp).map(ages);
		Mono<Integer> tot = Flux.fromIterable(employeeServiceImpl.findAllEmps())
				.flatMap(flatMapAge)
				.reduce((total, increment) -> total + increment);
		TotalAge total = new TotalAge();
		total.setTotal(tot.block());
		Mono<TotalAge> monoTot = Mono.justOrEmpty(total);
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(monoTot, TotalAge.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> averageAge(ServerRequest req) {
		ToIntFunction<Employee> sizeEmpArr = (e) -> {
			System.out.println("Thread: " + Thread.currentThread().getName());
			return e.getAge();
		};
		Callable<Double> task = () -> employeeServiceImpl.findAllEmps()
				.stream()
				.mapToInt(sizeEmpArr)
				.average()
				.getAsDouble();
		Mono<Double> aveAge= Mono.fromCallable(task).map((age) -> age * 0.25);
		AveAge average = new AveAge();
		average.setAverage(aveAge.block());
		Mono<AveAge> avgMono = Mono.justOrEmpty(average);
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(avgMono, AveAge.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
     public Mono<ServerResponse> countEmps(ServerRequest req) {
		
		Mono<Long> count = Flux.fromIterable(employeeServiceImpl.findAllEmps())
				.count();
		CountEmp countEmp = new CountEmp();
		countEmp.setCount(count.block());
		Mono<CountEmp> monoCntEmp = Mono.justOrEmpty(countEmp);
		return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(monoCntEmp, CountEmp.class)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
			
    public Mono<ServerResponse> saveEmployeeMono(ServerRequest req) {
    	Scheduler subWorker = Schedulers.newSingle("sub-thread");
    	Mono<Employee> employee = req.bodyToMono(Employee.class).doOnNext(employeeServiceImpl::saveEmployeeRec).subscribeOn(subWorker);
       	return ok().contentType(MediaType.APPLICATION_STREAM_JSON).build(employee.then());
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