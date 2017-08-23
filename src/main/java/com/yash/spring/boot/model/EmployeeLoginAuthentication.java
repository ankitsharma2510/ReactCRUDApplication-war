package com.yash.spring.boot.model;

public class EmployeeLoginAuthentication {

	private String employeeLoginId;
	private String password;

	public String getEmployeeLoginId() {
		return employeeLoginId;
	}

	public void setEmployeeLoginId(String employeeLoginId) {
		this.employeeLoginId = employeeLoginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
