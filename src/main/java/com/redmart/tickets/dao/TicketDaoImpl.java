package com.redmart.tickets.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import com.redmart.common.constants.TicketStatus;
import com.redmart.employee.model.Employee;
import com.redmart.tickets.model.Comments;
import com.redmart.tickets.model.Ticket;

public class TicketDaoImpl implements TicketDao 
{
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ticket> findTicketByAssignedUser(String emailId) {
		List<Ticket> ticketList = new ArrayList<Ticket>();
		ticketList = getSessionFactory().getCurrentSession()
			.createQuery("from Ticket where assignedTo.emailId=?").setParameter(0, emailId).list();
		if (ticketList.size() > 0) {
			Collections.sort(ticketList);
			return ticketList;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> findTicketByCreatedUser(String emailId) {
		List<Ticket> ticketList = new ArrayList<Ticket>();
		ticketList = getSessionFactory().getCurrentSession()
			.createQuery("from Ticket where createdBy.emailId=?").setParameter(0, emailId).list();
		if (ticketList.size() > 0) {
			Collections.sort(ticketList);
			return ticketList;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> findAllTickets() {
		List<Ticket> ticketList = new ArrayList<Ticket>();
		ticketList = getSessionFactory().getCurrentSession()
			.createQuery("from Ticket").list();
		if (ticketList.size() > 0) {
			Collections.sort(ticketList);
			return ticketList;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Ticket findTicketById(int id) {
		List<Ticket> ticketList = new ArrayList<Ticket>();
		ticketList = getSessionFactory().getCurrentSession()
			.createQuery("from Ticket where id=?").setParameter(0, id).list();
		if (ticketList.size() > 0) {
			return ticketList.get(0);
		} else {
			return new Ticket();
		}
	}
	
	public void save(Ticket ticket) {
		getSessionFactory().getCurrentSession().save(ticket);
	}

	public void update(Ticket ticket) 
	{
		getSessionFactory().getCurrentSession().update(ticket);
	}
	
	public void saveComment(Comments comments)
	{
		Date date = new Date();
		comments.setCommentCreateTime(new Timestamp(date.getTime()));
		getSessionFactory().getCurrentSession().save(comments);
	}

}
