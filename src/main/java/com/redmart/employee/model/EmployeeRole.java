package com.redmart.employee.model;

public class EmployeeRole {
	
	private Integer employeeRoleId;
	private Employee employee;
	private String role;
	public Integer getEmployeeRoleId() {
		return employeeRoleId;
	}
	public void setEmployeeRoleId(Integer employeeRoleId) {
		this.employeeRoleId = employeeRoleId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
