package org.packt.spring.boot.service;

public interface TimedService {
	
	public void batchFixedPeriod();
	public void batchCronPeriod();
	public void batchFixedDelay();
	public void batchInitialDelay();
}
