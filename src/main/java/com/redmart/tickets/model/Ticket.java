package com.redmart.tickets.model;

import java.sql.Timestamp;
import java.util.Set;

import com.redmart.common.constants.TicketStatus;
import com.redmart.customer.model.Customer;
import com.redmart.employee.model.Employee;

public class Ticket implements Comparable<Ticket>
{
	private int id;
	private Customer customer;
	private Employee createdBy;
	private Employee assignedTo;
	private String status = TicketStatus.ENUM_NEW;
	private String complaintArea;
	private Set<Comments> comments;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComplaintArea() {
		return complaintArea;
	}
	public void setComplaintArea(String complaintArea) {
		this.complaintArea = complaintArea;
	}
	public Employee getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}
	public Employee getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(Employee assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Set<Comments> getComments() {
		return comments;
	}
	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}
	public int compareTo(Ticket o) {
		if(this.getId() > o.getId())
			return 1;
		else if(this.getId() < o.getId())
		{
			return -1;
		}
		else 
			return 0;
	}
}
