package org.packt.spring.boot.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import org.packt.spring.boot.dao.EmployeeDao;
import org.packt.spring.boot.model.data.Employee;
import org.packt.spring.boot.model.form.EmployeeForm;
import org.packt.spring.boot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDaoImpl;

	@Async
	@Override
	public CompletableFuture<List<Employee>> readEmployees() {
		
		  Supplier<List<Employee>> supplyListEmp = ()->{
			System.out.println("service:readEmployees task executor: " + Thread.currentThread().getName());
			System.out.println("processing for 5000 ms");
			
			try {
				System.out.println("readEmployees Callable login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return employeeDaoImpl.getEmployees();
		};
		
		return CompletableFuture.supplyAsync(supplyListEmp);
	}
	
	
	@Override
	public Callable<List<Employee>> readEmployeesCall() {
		Callable< List<Employee> > task = new Callable< List<Employee> >() {
            @Override
            public  List<Employee>  call () throws Exception {
            	System.out.println("controller:readEmployeesCall task executor: " + Thread.currentThread().getName());
            	System.out.println("readEmployeesCall Callable login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                Thread.sleep(6000);
                List<Employee> empList = employeeDaoImpl.getEmployees();
                return empList;
            }
        };
		return task;
	}
	
	
	@Async
	@Override
	public void addEmployee(EmployeeForm empForm) {
		
		Employee emp = new Employee();
		emp.setDeptId(empForm.getEmpId());
		emp.setFirstName(empForm.getFirstName());
		emp.setLastName(empForm.getLastName());
		emp.setAge(empForm.getAge());
		emp.setBirthday(empForm.getBirthday());
		emp.setEmail(empForm.getEmail());
		emp.setDeptId(empForm.getDeptId());
		emp.setEmpId(empForm.getEmpId());
		try {
			System.out.println("service:addEmployee task executor: " + Thread.currentThread().getName());
			System.out.println("processing for 1000 ms");
			System.out.println("addEmployee @Async login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		employeeDaoImpl.addEmployeeBySJI(emp);
	}

	@Async
	public Future<Employee> readEmployee(Integer empId) {
		try {
			System.out.println("service:readEmployee(empid) task executor: " + Thread.currentThread().getName());
			System.out.println("processing for 2000 ms");
			System.out.println("readEmployee @Async login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		return new AsyncResult<>(employeeDaoImpl.getEmployee(empId));
	}

	@Async
	@Override
	public void updateEmployee(EmployeeForm empForm, int id) {
		Employee emp = new Employee();
		emp.setEmpId(empForm.getEmpId());
		emp.setFirstName(empForm.getFirstName());
		emp.setLastName(empForm.getLastName());
		emp.setAge(empForm.getAge());
		emp.setBirthday(empForm.getBirthday());
		emp.setEmail(empForm.getEmail());
		emp.setDeptId(empForm.getDeptId());
		emp.setId(id);
		try {
			System.out.println("service:updateEmployee task executor: " + Thread.currentThread().getName());
			System.out.println("processing for 1000 ms");
			System.out.println("updateEmployee @Async login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
	    employeeDaoImpl.updateEmployee(emp);
	}

	@Async
	@Override
	public void delEmployee(Integer empId) {
		try {
			System.out.println("service:delEmployee task executor: " + Thread.currentThread().getName());
			System.out.println("processing for 1000 ms");
			System.out.println("delEmployee @Async login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			Thread.sleep(500);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		employeeDaoImpl.delEmployee(empId);
		
	}

	@Override
	public Flux<String> readEmpFirstNames() {
		Function<Employee, Mono<String>> mapProcess = (emp) -> Mono.just(emp).map((e)->{
			System.out.println("flux:map task executor: " + Thread.currentThread().getName());
			System.out.println("flux:map task executor login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return e.getFirstName().toUpperCase();
		});
		Comparator<String> strComp = (s1, s2) ->{
			System.out.println("flux:sort task executor: " + Thread.currentThread().getName());
			System.out.println("flux:sort task executor login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return s1.compareTo(s2);
		};
		Flux<String> names = Flux.fromIterable(employeeDaoImpl.getEmployees()).flatMap(mapProcess).sort(strComp);
		return names;
	}

	@Override
	public Flux<Employee> readEmployeesFlux(int age) {
		Scheduler subWorker = Schedulers.newSingle("sub-thread");
		Scheduler pubWorker = Schedulers.newSingle("pub-thread");
		Predicate<Employee> validAge = (e) -> {
			System.out.println("flux:filter task executor: " + Thread.currentThread().getName());
			System.out.println("flux:filter task executor login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return e.getAge() > age;
		};
		Supplier<Flux<Employee>> deferredTask = ()->{
			System.out.println("flux:defer task executor: " + Thread.currentThread().getName());
			System.out.println("flux:defer task executor login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return Flux.fromIterable(employeeDaoImpl.getEmployees());
		};
		Flux<Employee> deferred = Flux.defer(deferredTask).filter(validAge).subscribeOn(subWorker).publishOn(pubWorker);
		return deferred;
	}

	@Override
	public Flux<Employee> readEmployeesByDescAge() {
		Scheduler subWorker = Schedulers.newSingle("sub-thread");
		Scheduler pubWorker = Schedulers.newSingle("pub-thread");
		Supplier<Flux<Employee>> deferredTask = ()->{
			System.out.println("flux:defer task executor: "+ Thread.currentThread().getName());
			System.out.println("flux:defer task executor login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return Flux.fromIterable(employeeDaoImpl.getEmployees());
		};
		Comparator<Employee> descAge = (e1, e2) -> {
			System.out.println("flux:sort task executor: " + Thread.currentThread().getName());
			System.out.println("flux:sort task executor login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			if(e1.getAge().compareTo(e2.getAge()) == 0){
				return 0;
			} else if(e1.getAge().compareTo(e2.getAge()) > 0){
				return -1;
			} else return 1;
		};
		Flux<Employee> deferred = Flux.defer(deferredTask).sort(descAge).subscribeOn(subWorker).publishOn(pubWorker);
		return deferred;
	}

	@Override
	public Flux<Employee> readEmployeesByAscLastName() {
		Scheduler subWorker = Schedulers.newSingle("sub-thread");
		Scheduler pubWorker = Schedulers.newSingle("pub-thread");
		Supplier<Flux<Employee>> deferredTask = ()->{
			System.out.println("flux:defer task executor: " + Thread.currentThread().getName());
			System.out.println("flux:defer task executor login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return Flux.fromIterable(employeeDaoImpl.getEmployees());
		};
		Comparator<Employee> descLName = (e1, e2) -> {
			System.out.println("flux:sort task executor: " + Thread.currentThread().getName());
			System.out.println("flux:sort task executor login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return e1.getLastName().compareTo(e2.getLastName());
		};
		Flux<Employee> deferred = Flux.defer(deferredTask).sort(descLName).subscribeOn(subWorker).publishOn(pubWorker);
		return deferred;
	}

	@Override
	public Mono<Double> getAveAge() {
		ToIntFunction<Employee> sizeEmpArr = (e) -> {
			System.out.println("flux:toIntFunction task executor: " + Thread.currentThread().getName());
			System.out.println("flux:toIntFunction task executor login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return e.getAge();
		};
		Callable<Double> task = () ->{
			System.out.println("flux:callable task executor: " + Thread.currentThread().getName());
			System.out.println("flux:callable task executor login: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return employeeDaoImpl.getEmployees().stream()
			.mapToInt(sizeEmpArr)
			.average()
			.getAsDouble();
		};

		Mono<Double> aveAge= Mono.fromCallable(task);
		return aveAge;
	}


}
