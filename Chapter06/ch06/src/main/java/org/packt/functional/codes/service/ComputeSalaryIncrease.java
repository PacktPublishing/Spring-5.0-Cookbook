package org.packt.functional.codes.service;

@FunctionalInterface
public interface ComputeSalaryIncrease {
	public double increase(double current, double increase);
	
	default public double demote(double current, double decrease){
		
		return current - (0.2*decrease);
	}
	
	static public double rateAppraisal(double current){
		return current * 0.2;
	}
}
