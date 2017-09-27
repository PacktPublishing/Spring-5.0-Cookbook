package org.packt.reactive.codes.service;

import java.util.List;

import org.packt.reactive.codes.model.data.Employee;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observables.ConnectableObservable;

public interface EmployeeRxJavaService {
	public Observable<Employee> getEmployeesRx();
	public Single<Employee> getEmployeeRx(int empid);
	public Flowable<String> getFirstNamesRx();
	public Flowable<String> getEmpNamesRx();
	public Flowable<String> getEmpNamesParallelRx();
	public Flowable<String> combinedStreamRx(List<String> others);
	public ConnectableObservable<String> freeFlowEmps();
}
