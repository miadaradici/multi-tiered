package com.multitired.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.multitired.service.ProjectService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {

	
	@Autowired
	ProjectService projectService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		//CREATE
		//userService.addUser()
		
		//projectService.getProject();
		
		
		return "home";
	}
}