package com.redmart.customer.dao;

import java.util.List;

import com.redmart.customer.model.Customer;

public interface CustomerDao {

	Customer findByEmailId(String emailId);
	void save(Customer customer);
	List<String> findAllCustomerMailId();
}
