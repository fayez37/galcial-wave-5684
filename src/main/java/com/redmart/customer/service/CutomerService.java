package com.redmart.customer.service;

import java.util.List;

import com.redmart.customer.dao.CustomerDao;
import com.redmart.customer.model.Customer;

public class CutomerService {
	
	private CustomerDao customerDao;
	
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void save(Customer customer)
	{
		customerDao.save(customer);
	}
	
	public Customer findCustomerByMailId(String emailId)
	{
		return customerDao.findByEmailId(emailId);
	}
	
	public List<String> findAllCustomerMailId()
	{
		return customerDao.findAllCustomerMailId();
	}
}
