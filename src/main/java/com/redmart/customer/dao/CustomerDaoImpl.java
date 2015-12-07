package com.redmart.customer.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.redmart.customer.model.Customer;

public class CustomerDaoImpl implements CustomerDao {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public Customer findByEmailId(String emailId) {
		List<Customer> customerList = new ArrayList<Customer>();
		customerList = getSessionFactory().getCurrentSession()
			.createQuery("from Customer where emailId=?").setParameter(0, emailId).list();
		if (customerList.size() > 0) {
			return customerList.get(0);
		} else {
			return null;
		}
	}

	public void save(Customer customer) {
		getSessionFactory().getCurrentSession().saveOrUpdate(customer);
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<String> findAllCustomerMailId() {
		List<String> customerList = new ArrayList<String>();
		customerList = getSessionFactory().getCurrentSession()
			.createQuery("select emailId from Customer").list();
		if (customerList.size() > 0) {
			return customerList;
		} else {
			return null;
		}
	}

}