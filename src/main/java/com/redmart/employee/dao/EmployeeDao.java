package com.redmart.employee.dao;

import java.util.List;

import com.redmart.employee.model.Employee;

public interface EmployeeDao {

	Employee findByEmailId(String emailId);
	List<String> findAllEmployeeMailId(String emailId);
	void save(Employee employee);
	void update(Employee employee);
}