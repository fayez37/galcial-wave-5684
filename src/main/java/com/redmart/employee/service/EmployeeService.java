package com.redmart.employee.service;

import com.redmart.employee.dao.EmployeeDao;
import com.redmart.employee.model.Employee;

public class EmployeeService {
	
	private EmployeeDao employeeDao;
	
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	public void update(Employee employee)
	{
		employeeDao.update(employee);
	}

	public void save(Employee employee)
	{
		employeeDao.save(employee);
	}
}
