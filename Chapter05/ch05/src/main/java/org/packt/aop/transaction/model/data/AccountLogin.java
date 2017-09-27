package org.packt.aop.transaction.model.data;

public class AccountLogin {

	private Integer id;
	private String username;
	private String password;
	private String encPassword;
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getEncPassword() {
		return this.encPassword;
	}

	public void setEncPassword(String encPassword) {
		this.encPassword = encPassword;
	}



}
