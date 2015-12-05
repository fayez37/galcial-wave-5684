package com.redmart.employee.dao;

import com.redmart.employee.model.Employee;

public interface EmployeeDao {

	Employee findByEmailId(String emailId);
	void save(Employee employee);
	void update(Employee employee);
}