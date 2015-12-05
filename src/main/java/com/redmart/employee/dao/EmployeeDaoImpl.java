package com.redmart.employee.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.redmart.employee.model.Employee;
import com.redmart.employee.model.EmployeeRole;

public class EmployeeDaoImpl implements EmployeeDao {

	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Employee findByEmailId(String username) {

		List<Employee> employee = new ArrayList<Employee>();
		employee = getSessionFactory().getCurrentSession()
			.createQuery("from Employee where emailId=?").setParameter(0, username).list();
		if (employee.size() > 0) {
			return employee.get(0);
		} else {
			return null;
		}

	}
	
	public void save(Employee employee) {
		EmployeeRole employeeRole = new EmployeeRole();
		employeeRole.setRole("ROLE_USER");
		employeeRole.setEmployee(employee);
		employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
		Set<EmployeeRole> employeRoleSet = new HashSet<EmployeeRole>();
		employeRoleSet.add(employeeRole);
		
		employee.setEnabled(true);
		employee.setEmployeeRole(employeRoleSet);
		getSessionFactory().getCurrentSession().save(employee);
	}
	
	public void update(Employee employee) {
		employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
		employee.setEnabled(true);
		getSessionFactory().getCurrentSession().update(employee);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
