package org.packt.reactive.codes.service.impl;

import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import org.packt.reactive.codes.dao.EmployeeDao;
import org.packt.reactive.codes.model.data.Employee;
import org.packt.reactive.codes.service.EmployeeStreamService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeStreamServiceImpl implements EmployeeStreamService {
	
	@Autowired
	private EmployeeDao employeeDaoImpl;

	@Override
	public Publisher<Employee> readEmployees() {
		Publisher<Employee> publishedEmployees= Flux.fromIterable(employeeDaoImpl.getEmployees());
		return publishedEmployees;
	}

	
	@Override
	public Publisher<Employee> readEmployee(Integer empId) {
		
		Callable<Employee> task = () -> employeeDaoImpl.getEmployee(empId);
		Publisher<Employee> publishedEmployee = Mono.fromCallable(task);
		return publishedEmployee;
	}

	
	@Override
	public void delEmployee(Integer empId) {
		employeeDaoImpl.delEmployee(empId);
		
	}

	@Override
	public Publisher<String> getFirstNamesFlow() {
		Function<Employee, String> firstNamesFunc= (e) -> e.getFirstName();
		Publisher<String> firstNameStream= Flux.fromIterable(employeeDaoImpl.getEmployees()).map(firstNamesFunc);
		return firstNameStream;
	}
	
	
	@Override
	public Mono<Double> getAveAge() {
		ToIntFunction<Employee> sizeEmpArr = (e) -> {
			System.out.println("Thread: " + Thread.currentThread().getName());
			return e.getAge();
		};
		Callable<Double> task = () -> employeeDaoImpl.getEmployees()
				.stream()
				.mapToInt(sizeEmpArr)
				.average()
				.getAsDouble();
		Mono<Double> aveAge= Mono.fromCallable(task).map((age) -> age * 0.25);
		return aveAge;
	}


	@Override
	public Publisher<String> getValidEmployees(){
		Function<Employee, String> validEmps = (e) -> {
			double ageGradient = (int) ( 1 / (e.getAge() - 22));
			if (ageGradient == 0){
				return e.getFirstName() + " " + e.getLastName();
			}else{
				return null;
			}
		};
		
		Runnable completion = () ->{
			System.out.println("******** End of List*********");
		};
		Publisher<String> publishedEmployees= Flux
				.fromIterable(employeeDaoImpl.getEmployees())
				.map(validEmps)
				.onErrorReturn("Invalid Employee")
				.doOnComplete(completion);
		return publishedEmployees;
	}


	@Override
	public Mono<Employee> getEmptyMonoStream() {
		Mono<Employee> emptyEmp = Mono.empty();
		return emptyEmp;
	}


	@Override
	public Flux<String> getFirstNames() {
		Function<Employee, String> firstNames = (e) -> e.getFirstName();
		Predicate<String> validateStr = (fname) -> fname.endsWith("in");
		Comparator<String> descFName = (f1, f2) -> f1.compareTo(f2);
		Flux<String> empFirstNames = Flux.fromIterable(employeeDaoImpl.getEmployees())
										  .map(firstNames)
										  .filter(validateStr)
										  .sort(descFName);
		return empFirstNames;
	}


	@Override
	public Flux<Employee> getEmptyFluxStream() {
		Flux<Employee> empListEmpty = Flux.empty();
		return empListEmpty;
	}


	@Override
	public Flux<Double> getStandardSalary() {
		Double salary[] = {10000.00, 25000.00, 50000.00, 60000.00};
		Comparator<Double> ascSort = (s1, s2) -> s1.compareTo(s2);
		Flux<Double> salaryStream = Flux.fromArray(salary).sort(ascSort);
		return salaryStream;
	}


	@Override
	public Publisher<Void> showThreads() {
		Runnable task = () ->{
			System.out.println(Thread.currentThread().getName());
		};
		Mono<Void> execThread = Mono.fromRunnable(task);
		return execThread;
	}



	

}
