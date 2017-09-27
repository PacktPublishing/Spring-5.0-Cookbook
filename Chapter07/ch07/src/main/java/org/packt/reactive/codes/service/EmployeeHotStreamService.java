package org.packt.reactive.codes.service;

import java.util.List;

import reactor.core.publisher.ConnectableFlux;

public interface EmployeeHotStreamService {
	public ConnectableFlux<String> freeFlowEmps();
	public void monoProcessorGetEmployee(Integer id);
	public void fluxProcessorGetEmployee(List<Integer> ids);
	public void validateNamesTopic(List<String> names);
	public void validateNamesWorkQueue(List<String> names);
	public void validateNamesReplay(List<String> names);
	public void validateNamesUnicast(List<String> names);
}
