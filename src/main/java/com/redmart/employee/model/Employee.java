package com.redmart.employee.model;

import java.util.HashSet;
import java.util.Set;

public class Employee {
	
	private String emailId;
	private String password;
	private boolean enabled;
	private Set<EmployeeRole> employeeRole = new HashSet<EmployeeRole>(0);
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Set<EmployeeRole> getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(Set<EmployeeRole> employeeRole) {
		this.employeeRole = employeeRole;
	}
}
