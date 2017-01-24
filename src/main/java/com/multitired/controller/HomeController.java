package com.multitired.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.multitired.service.ProjectService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping({ "/", "/index" })
public class HomeController {

	@Autowired
	ProjectService projectService;

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		// CREATE
		// userService.addUser()

		// projectService.getProject();

		return "index";
	}
}