package com.eby.bestapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eby.bestapp.dao.UserDAO;
import com.eby.bestapp.model.User;
import com.eby.bestapp.service.ProjectService;
import com.eby.bestapp.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProjectService projectService;

//	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		System.out.println("new request");
		//User u = userDAO.get(1);
		userService.getUser();
		//System.out.println(u.getName());
		
		
		projectService.getProject();
		
		return "home";
	}
}