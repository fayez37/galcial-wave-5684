package com.redmart.tickets.service;

import java.util.List;

import com.redmart.tickets.dao.TicketDao;
import com.redmart.tickets.model.Comments;
import com.redmart.tickets.model.Ticket;

public class TicketService 
{
	private TicketDao ticketDao;
	
	public TicketDao getTicketDao() {
		return ticketDao;
	}

	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	public void save(Ticket ticket)
	{
		ticketDao.save(ticket);
	}
	
	public void update(Ticket ticket)
	{
		ticketDao.update(ticket);
	}
	
	public List<Ticket> findTicketByAssignedUser(String emailId)
	{
		return ticketDao.findTicketByAssignedUser(emailId);
	}
	
	public List<Ticket> findTicketByCreatedUser(String emailId)
	{
		return ticketDao.findTicketByCreatedUser(emailId);
	}
	
	public List<Ticket> findAllTickets()
	{
		return ticketDao.findAllTickets();
	}
	
	public Ticket findTicketById(int id)
	{
		return ticketDao.findTicketById(id);
	}

	public void saveComment(Comments comments) 
	{
		ticketDao.saveComment(comments);
	}
}
