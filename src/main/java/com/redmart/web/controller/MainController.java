package com.redmart.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.redmart.common.constants.ComplaintAreas;
import com.redmart.common.constants.TicketStatus;
import com.redmart.customer.service.CutomerService;
import com.redmart.employee.model.Employee;
import com.redmart.employee.service.EmployeeService;
import com.redmart.tickets.model.Comments;
import com.redmart.tickets.model.Ticket;
import com.redmart.tickets.service.TicketService;

@Controller
public class MainController {
	
    private EmployeeService employeeService;
    private TicketService ticketService;
    private CutomerService customerService;
    
    @Autowired(required=true)
    @Qualifier(value="employeeService")
    public void setEmployeeService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    
    @Autowired(required=true)
    @Qualifier(value="ticketService")
    public void setTicketService(TicketService ticketService){
        this.ticketService = ticketService;
    }
    
    @Autowired(required=true)
    @Qualifier(value="customerService")
    public void setCustomerService(CutomerService customerService){
        this.customerService = customerService;
    }
    
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		ModelAndView model = new ModelAndView();
		String emailId = getEmailIdOfUser();
		List<Ticket> ticketList = ticketService.findTicketByAssignedUser(emailId);
		List<Ticket> createdticketList = ticketService.findTicketByCreatedUser(emailId);
		
		model.addObject("title", "Manage your tickets");
		model.addObject("ticketList", ticketList);
		model.addObject("createdticketList", createdticketList);
		model.setViewName("welcome");
		return model;

	}

	private String getEmailIdOfUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		String emailId =  userDetail.getUsername();
		return emailId;
	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Welcome Customer Complaint System Administrator");
		model.setViewName("admin");
		List<Ticket> ticketList = ticketService.findAllTickets();
		model.addObject("ticketList", ticketList);
		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() 
	{
		ModelAndView model = new ModelAndView();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());

		}
		model.setViewName("403");
		return model;

	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView changePassword(ModelAndView model, HttpServletRequest request) {
	    Employee employee = new Employee();
	    model.addObject("employee", employee);
	    model.setViewName("changePassword");
	    return model;
	}
	
	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	public ModelAndView savePassword(@ModelAttribute Employee employee, HttpServletRequest request) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    employee.setEmailId(userDetail.getUsername());
		employeeService.update(employee);
		if(request.isUserInRole("ROLE_ADMIN"))
		{
			return new ModelAndView("redirect:/admin");
		}
		if(request.isUserInRole("ROLE_USER"))
		{
			return new ModelAndView("redirect:/welcome");
		}
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUser(ModelAndView model) 
	{
	    Employee employee = new Employee();
	    model.addObject("networkElement", employee);
	    model.setViewName("newUser");
	    return model;
	}
	
	@RequestMapping(value = "/createNewUser", method = RequestMethod.POST)
	public ModelAndView createNewUser(@ModelAttribute Employee employee) 
	{
		if(EmailValidator.getInstance().isValid(employee.getEmailId()))
		{
			employeeService.save(employee);
			return new ModelAndView("redirect:/admin");
		}
		else
		{
			ModelAndView model = new ModelAndView("newUser");
			model.addObject("networkElement", employee);
		    model.addObject("error","Invalid email id");
		    return model;
		}
	}
	
	@RequestMapping(value = "/newTicket/{id}", method = RequestMethod.GET)
    public ModelAndView newTicket(@PathVariable Integer id) 
	{
		ModelAndView model = new ModelAndView("editTicket");
		Ticket ticket = ticketService.findTicketById(id);
		if(id == 0)
		{
			Employee employee = new Employee();
			employee.setEmailId(getEmailIdOfUser());
			ticket.setCreatedBy(employee);
		}
		model.addObject("ticket", ticket);
		List<String> customerEmailList = customerService.findAllCustomerMailId();
		List<String> employeeEmailList = employeeService.findAllEmployeeMailId(getEmailIdOfUser());
		List<String> complaintList = getComplaintList();
		List<String> statusList = getStatusValue();
		model.addObject("customerEmailList", customerEmailList);
		model.addObject("employeeEmailList", employeeEmailList);
		model.addObject("statusList", statusList);
		model.addObject("complaintList", complaintList);
		
    	return model;
	}
    
    /**
     * Saves the edited person and display all persons again
     * @return
     */
    @RequestMapping(value = "/newTicket/{id}", method = RequestMethod.POST)
    public ModelAndView saveTicket(@ModelAttribute("ticket") Ticket ticket, 
    		@PathVariable Integer id, ModelAndView model) {
    	if(id == 0)
    	{
    		/*Employee employee = new Employee();
    		employee.setEmailId(getEmailIdOfUser());
    		ticket.setCreatedBy(employee);*/
    		ticket.setStatus(TicketStatus.ENUM_OPEN);
    		ticketService.save(ticket);
    	}
    	else
    	{
    		ticket.setId(id);
    		ticketService.update(ticket);
    	}
    	return new ModelAndView("redirect:/welcome");
	}
    
    @RequestMapping(value = "/newComment/{id}", method = RequestMethod.GET)
    public ModelAndView newComment(@PathVariable Integer id) 
	{
		ModelAndView model = new ModelAndView("createComment");
		Comments comment = new Comments();
		Ticket ticket = new Ticket();
    	ticket.setId(id);
		comment.setTicket(ticket);
		model.addObject("comment",comment);
    	return model;
	}
    
    /**
     * Saves the edited person and display all persons again
     * @return
     */
    @RequestMapping(value = "/newComment/{id}", method = RequestMethod.POST)
    public ModelAndView saveComment(@ModelAttribute("comment") Comments comments,@PathVariable Integer id, ModelAndView model) 
    {
    	Employee employee = new Employee();
    	/*Ticket ticket = new Ticket();
    	ticket.setId(id);*/
   		employee.setEmailId(getEmailIdOfUser());
   		comments.setEmployee(employee);
   		//comments.setTicket(ticket);
   		ticketService.saveComment(comments);
    	return new ModelAndView("redirect:/newTicket/"+id);
	}
    
    private List<String> getStatusValue()
    {
    	List<String> statusList = new ArrayList<String>();
    	statusList.add(TicketStatus.ENUM_OPEN);
    	statusList.add(TicketStatus.ENUM_CLOSED);
    	return statusList;
    }
    
    private List<String> getComplaintList() 
    {
    	System.out.println("In");
    	List<String> complaintList = new ArrayList<String>();
    	complaintList.add(ComplaintAreas.ENUM_ACCOUNT);
    	complaintList.add(ComplaintAreas.ENUM_DELIVERY);
    	complaintList.add(ComplaintAreas.ENUM_ITEMS);
    	complaintList.add(ComplaintAreas.ENUM_PAYMENT);
    	complaintList.add(ComplaintAreas.ENUM_ENHANCEMENTS);
		return complaintList;
	}
}
