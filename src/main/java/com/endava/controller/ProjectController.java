package com.endava.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.endava.exceptions.OperationNotPermitted;
import com.endava.exceptions.ProjectNotFoundException;
import com.endava.exceptions.ResourceNotFoundException;
import com.endava.exceptions.UserNotFoundException;
import com.endava.model.MetaProject;
import com.endava.model.MetaUser;
import com.endava.model.Project;
import com.endava.model.User;
import com.endava.service.ProjectService;
import com.endava.utils.ResponseMessage;

@Controller
public class ProjectController {

	@Autowired
	ProjectService projectService;

	public static final Logger LOG = Logger.getLogger(ProjectController.class);

	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> getProject(@PathVariable Integer id) {
		LOG.info("A request to GET project/" + id + " has made");
		try {
			Project project = projectService.getProjectById(id);
			return new ResponseEntity<Project>(project, HttpStatus.OK);
		} catch (ProjectNotFoundException e) {
			LOG.error("Project with id " + id + " not found");
		}
		return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/projects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<Project> getProjects() {
		Collection<Project> projects = projectService.getProjects();
		if (projects == null) {
			LOG.error(ResponseMessage.RESOURCE_NOT_FOUND);
			throw new ResourceNotFoundException();
		}
		LOG.info(ResponseMessage.SUCCESS);
		return projects;
	}

	@RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Project> deleteProject(@PathVariable Integer id) {
		LOG.info("A request to DELETE project/" + id + " has made");
		try {
			projectService.deleteProject(id);
		} catch (ProjectNotFoundException e) {
			LOG.error("Project with id" + id + " not found");
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		} catch (OperationNotPermitted e) {
			LOG.error("Project with id " + id + " cannot be deleted");
			return new ResponseEntity<Project>(HttpStatus.FORBIDDEN);
		}
		LOG.info(ResponseMessage.SUCCESS);
		return new ResponseEntity<Project>(HttpStatus.OK);
	}

	@RequestMapping(value = "/project", method = RequestMethod.POST, consumes = { "application/json;charset=UTF-8" })
	public ResponseEntity<Object> createProject(@RequestBody MetaProject project) {
		LOG.info("A request to POST /project has made");
		try {
			projectService.createProject(project);
		} catch (UserNotFoundException e) {
			LOG.error(ResponseMessage.RESOURCE_NOT_FOUND);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOG.error(ResponseMessage.INTERNAL_ERROR);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info(ResponseMessage.SUCCESS);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	// @RequestMapping(value = "/getProject", method = RequestMethod.GET)
	// public String getUser(@RequestParam Integer projectId) {
	// Project project = projectService.getProjectById(projectId);
	// return "home";
	// }

	// @RequestMapping(value = "/add", method = RequestMethod.POST)
	// public String createUser(@RequestParam Project project) {
	// projectService.createProject(project);
	// return "home";
	// }
	//
	// @RequestMapping(value = "/modify", method = RequestMethod.PUT)
	// public String updateUser(@RequestParam Project project) {
	// projectService.updateProject(project);
	// return "home";
	// }

}
