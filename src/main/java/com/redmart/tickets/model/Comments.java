package com.redmart.tickets.model;

import java.sql.Timestamp;

import com.redmart.employee.model.Employee;

public class Comments
{
	private int id;
	private Ticket ticket;
	private Employee employee;
	private String comments;
	private Timestamp commentCreateTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public Timestamp getCommentCreateTime() {
		return commentCreateTime;
	}
	public void setCommentCreateTime(Timestamp commentCreateTime) {
		this.commentCreateTime = commentCreateTime;
	}
}
