package com.redmart.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.redmart.employee.service.EmployeeService;

@Controller
public class MainController {
	
    private EmployeeService employeeService;
    
    @Autowired(required=true)
    @Qualifier(value="employeeService")
    public void setEmployeeService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Network Element Setup Manager");
		model.addObject("message", "Network Element Setup Manager");
		model.setViewName("login");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Welcome Network Element Adminstrator");
		model.addObject("message", "To EDIT or ADD Network Elements");
		model.setViewName("admin");
		
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
	public ModelAndView accesssDenied() {

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
	
	/*@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView changePassword(ModelAndView model, HttpServletRequest request) {
	    Employee employee = new Employee();
	    model.addObject("networkElement", employee);
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
	public ModelAndView newUser(ModelAndView model) {
	    Employee employee = new Employee();
	    model.addObject("networkElement", employee);
	    model.setViewName("newUser");
	    return model;
	}
	
	@RequestMapping(value = "/createNewUser", method = RequestMethod.POST)
	public ModelAndView createNewUser(@ModelAttribute Employee employee) {
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
	}*/
}
