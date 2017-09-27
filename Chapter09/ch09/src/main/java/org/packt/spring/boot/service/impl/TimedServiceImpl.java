package org.packt.spring.boot.service.impl;

import org.packt.spring.boot.service.TimedService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TimedServiceImpl implements TimedService{
	
	
	@Scheduled(fixedRate=2000)
	@Override
	public void batchFixedPeriod() {
		System.out.println("scheduled#batchFixedPeriod: " + Thread.currentThread().getName());
	}

	@Scheduled(cron="*/5 * * * * ?")
	@Override
	public void batchCronPeriod() {
		System.out.println("scheduled#batchCronPeriod: " + Thread.currentThread().getName());
	}

	@Scheduled(fixedRate=5000, initialDelay=2000)
	@Override
	public void batchInitialDelay() {
		System.out.println("scheduled#batchInitialDelay: " + Thread.currentThread().getName());
	}
	
	@Scheduled(fixedDelay=1000)
	@Override
	public void batchFixedDelay() {
		System.out.println("scheduled#batchFixedDelay: " + Thread.currentThread().getName());
	}

}
