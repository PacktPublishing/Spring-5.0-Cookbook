package org.packt.microservice.instance.model.data;

import java.io.Serializable;

public class CountDept implements Serializable{
	private Long count;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
