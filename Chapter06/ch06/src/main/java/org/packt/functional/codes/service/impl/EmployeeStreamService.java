package org.packt.functional.codes.service.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.packt.functional.codes.dao.EmployeeDao;
import org.packt.functional.codes.model.data.Employee;
import org.packt.functional.codes.service.AgeLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeStreamService")
public class EmployeeStreamService {
	
	@Autowired
	private EmployeeDao employeeDaoImpl;
	
    @SuppressWarnings("unused")
	public void getCollectionStreams(){
		Stream<Employee> serial = employeeDaoImpl.getEmployees().stream();
		Stream<Employee> parallel = employeeDaoImpl.getEmployees().parallelStream();
	}
    
    public void getListData(){
    	List<String> employeeIDS =
    		    Arrays.asList("23234", "353453", "22222", "5555", "676767");
    	Stream<String> streamsIds = employeeIDS.stream();
    	
    	Set<String> candidates = new HashSet<String>();
    	candidates.add("Joel");
    	candidates.add("Candy");
    	candidates.add("Sherwin");
    	Stream<String> streamCandidates = candidates.stream();
    	
    }
    
    public void getArrayData(){
    	int[] ages = {24, 33, 21, 22, 45};
    	IntStream ageStream = Arrays.stream(ages);
    	
    	double[] salaries = {33000.50, 23100.20, 45000.50};
    	DoubleStream salStream = Arrays.stream(salaries);
    	
    	long[] longDates = {23434432342L, 11123343435L, 34343342343L};
    	LongStream dateStream = Arrays.stream(longDates);
    }
    
    public Stream<Employee> createEmptyArrayStream(){
    	Stream<Employee> stream = Stream.empty();
    	
    	return stream;
    }
    
    /////////////////////////////////////////////////////////////////////
    
    
    public Stream<Employee> createUnlimitedStream(){
    	Stream<Employee> stream = Stream.generate(() -> { return new Employee();}).limit(10);
    	
    	return stream;
    }
    
    public Stream<Employee> createArrayFromOf(Employee[] empArr){
    	Stream<Employee> empStream = Stream.of(empArr);
    	
    	return empStream;
    }
    
    public void createCustomArrayStream(){
    	Employee emp1 = new Employee();
    	Employee emp2 = new Employee();
    	Stream<Employee> emptyRecs = Stream.of(emp1, emp2);
    	Consumer<Employee> showRecs = System.out::println;
    	emptyRecs.forEach(showRecs);
    }
    
       
    public Stream<Employee> createArrayStream(Employee[] empArr){
    	Stream<Employee> empStream = Arrays.stream(empArr);
    	
    	return empStream;
    }
    
   
    
    public Employee[] convertStreamToArray(Stream<Employee> arrStream){
    	Employee[] newArrayEmps = arrStream.toArray(Employee[]::new);
    	return newArrayEmps;
    }
    
    
    public Employee[] arrayStream(){
    	Stream<Employee> serial = employeeDaoImpl.getEmployees().stream();
    	IntFunction<Employee[]> sizeEmpArr = (size) -> new Employee[size];
    	Employee[] arrEmps = serial.toArray(sizeEmpArr);
    	return arrEmps;
    }
    
        
    
    public List<Employee> getEmployeesAge(){
  		AgeLimitService ageLimit = ()->{	
  			return employeeDaoImpl.getEmployees()
  					.stream()
  					.filter((Employee e)-> e.getAge() > 25)
  					.collect(Collectors.toList());
  		};
  		
  		return ageLimit.qualifiedEmps();
  	}
    
    public boolean validateAgeNotQualified(){
    	Predicate<Employee> checkNotQualified = (e) -> e.getAge() < 25;
    	return employeeDaoImpl.getEmployees().stream().anyMatch(checkNotQualified);
    }
    
    public List<Employee> getSortedEmployees(){
    	
    	Comparator<Employee> compareEmp = (e1, e2) -> e1.getLastName().compareTo(e2.getLastName());
    	List<Employee> newListEmps = employeeDaoImpl.getEmployees()
    			     .stream()
    			     .sorted(compareEmp )
    			     .collect(Collectors.toList());
    
		return newListEmps;
	}
    
    public List<Employee> getOldestEmps(){
    	Predicate<Employee> checkNotQualified = (e) -> e.getAge() < 25;
    	Comparator<Employee> compareAge = (e1, e2) -> e1.getAge().compareTo(e2.getAge());
    	return employeeDaoImpl.getEmployees().stream()
    			.filter(checkNotQualified)
    			.sorted(compareAge)
    			.limit(3)
    			.distinct()
    			.collect(Collectors.toList());
    }
    
    public Set<String> getDistinctNames(){
    	Function<Employee,String> allNames = (e) -> e.getFirstName();
    	Set<String> setNames = employeeDaoImpl.getEmployees()
			     .stream()
			     .filter((a) -> a.getAge() > 25)
			     .map(allNames)
			     .collect(Collectors.toCollection(LinkedHashSet::new));
    	
    	return setNames;
    	
    }
    
    public double sumAge(){
    	BinaryOperator<Integer> addAgeEmp = (age1, age2) -> age1 + age2;
    	Function<Employee,Integer> ageList = (e) -> e.getAge();
    	double sum = employeeDaoImpl.getEmployees()
			     .stream()
			     .map(ageList)
			     .reduce(0, addAgeEmp);
    	
    	return sum;
    }
    
    public double getAverageAge(){
    	ToIntFunction<Employee> sizeEmpArr = (e) -> e.getAge();
		return employeeDaoImpl.getEmployees().stream().mapToInt(sizeEmpArr).average().getAsDouble();
	}
	
    
    public List<Employee> getEmployeesFunc(){
    	
    	Predicate<Employee> qualifiedEmps = (e) -> e.getAge() > 25;
    	List<Employee> newListEmps = employeeDaoImpl.getEmployees()
    			     .stream()
    			     .filter(qualifiedEmps)
    			     .collect(Collectors.toList());
    
		return newListEmps;
	}
    
    public List<Employee> updateNames(){
    	List<Employee> newListEmps= employeeDaoImpl.getEmployees();
    	newListEmps.replaceAll((e) ->{
    		e.setFirstName(e.getFirstName().toUpperCase());
    		e.setLastName(e.getLastName().toUpperCase());
    		return e;
    	});
    	
    	return newListEmps;
    }
    
    public void showAllEmployees(){
    	Consumer<Employee> showAll = (e) -> {
    		System.out.format("%s %s %d\n", e.getFirstName(), e.getLastName(), e.getAge());
    	};
    	employeeDaoImpl.getEmployees()
    			.stream()
    			.forEach(showAll);
    }
	
}
