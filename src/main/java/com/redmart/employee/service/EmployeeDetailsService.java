package com.redmart.employee.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.redmart.employee.dao.EmployeeDao;
import com.redmart.employee.model.Employee;
import com.redmart.employee.model.EmployeeRole;

public class EmployeeDetailsService implements UserDetailsService {
	private EmployeeDao employeeDao;

	@Override
	public UserDetails loadUserByUsername(final String username) 
               throws UsernameNotFoundException {

		com.redmart.employee.model.Employee employee = employeeDao.findByEmailId(username);
		List<GrantedAuthority> authorities = buildUserAuthority(employee.getEmployeeRole());

		return buildUserForAuthentication(employee, authorities);
		

	}

	// Converts com.redmart.employee.model.Employee user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(Employee employee, 
		List<GrantedAuthority> authorities) {
		return new User(employee.getEmailId(), 
			employee.getPassword(), employee.isEnabled(), 
                        true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<EmployeeRole> employeeRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (EmployeeRole employeeRole : employeeRoles) {
			setAuths.add(new SimpleGrantedAuthority(employeeRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

}
