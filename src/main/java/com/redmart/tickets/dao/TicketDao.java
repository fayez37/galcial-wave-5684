package com.redmart.tickets.dao;

import java.util.List;

import com.redmart.tickets.model.Comments;
import com.redmart.tickets.model.Ticket;

public interface TicketDao 
{
	List<Ticket> findTicketByAssignedUser(String emailId);
	List<Ticket> findTicketByCreatedUser(String emailId);
	List<Ticket> findAllTickets();
	Ticket findTicketById(int id);

	void save(Ticket ticket);
	void update(Ticket ticket);
	void saveComment(Comments comments);
}
