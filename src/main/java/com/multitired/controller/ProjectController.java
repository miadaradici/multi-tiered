package com.multitired.controller;

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

import com.multitired.exceptions.OperationNotPermitted;
import com.multitired.exceptions.ProjectNotFoundException;
import com.multitired.exceptions.ResourceNotFoundException;
import com.multitired.exceptions.UserNotFoundException;
import com.multitired.model.MetaProject;
import com.multitired.model.MetaUser;
import com.multitired.model.Project;
import com.multitired.model.User;
import com.multitired.service.ProjectService;
import com.multitired.service.UserService;
import com.multitired.utils.ResponseMessage;

@Controller
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@Autowired
	UserService userService;

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

	@RequestMapping(value = "/project/{projectId}/user/{userId}", method = RequestMethod.POST, consumes = {
			"application/json;charset=UTF-8" })
	public ResponseEntity<Object> addUserToProject(@PathVariable Integer projectId, @PathVariable Integer userId) {
		LOG.info("A request to POST /project/user was made");
		try {
			Project project = projectService.getProjectById(projectId);
			User user = userService.getUserById(userId);
			project.getParticipants().add(user);
			projectService.saveOrUpdate(project);
		} catch (ProjectNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		LOG.info(ResponseMessage.SUCCESS);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
