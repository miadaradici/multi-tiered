package com.endava.controller;

import java.util.Collection;
import java.util.Date;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.endava.exceptions.OperationNotPermitted;
import com.endava.exceptions.ProjectNotFoundException;
import com.endava.exceptions.ResourceNotFoundException;
import com.endava.exceptions.SprintNotFoundException;
import com.endava.exceptions.UserNotFoundException;
import com.endava.model.MetaProject;
import com.endava.model.MetaSprint;
import com.endava.model.Project;
import com.endava.model.Sprint;
import com.endava.model.User;
import com.endava.service.SprintService;
import com.endava.service.UserService;
import com.endava.utils.ResponseMessage;

@Controller
public class SprintController {

	public static final Logger LOG = Logger.getLogger(SprintController.class);

	@Autowired
	SprintService sprintService;

	@RequestMapping(value = "/sprint/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sprint> getSprint(@PathVariable Integer id) {
		LOG.info("A request to GET sprint/" + id + " has made");
		try {
			Sprint sprint = sprintService.getSprintById(id);
			LOG.info("Returning sprint with id " + id);
			return new ResponseEntity<Sprint>(sprint, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			LOG.error("Sprint with id" + id + "not found");
		}
		return new ResponseEntity<Sprint>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/project/{projectId}/sprints", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<Sprint> getSprints(@PathVariable Integer projectId) {
		LOG.info("A request to GET project/" + projectId + "/sprints has made");
		try {
			Collection<Sprint> sprints = sprintService.getProjectsSprints(projectId);
			LOG.info("Returning sprints of project with id " + projectId);
			return sprints;
		} catch (ProjectNotFoundException e) {
			LOG.error("Project with id" + projectId + "not found");
		} catch (SprintNotFoundException e) {
			LOG.error("Sprints of project with id" + projectId + "not found");
		}
		throw new ResourceNotFoundException();
	}

	@RequestMapping(value = "/sprint/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Sprint> deleteProject(@PathVariable Integer id) {
		LOG.info("A request to DELETE sprint/" + id + " has made");
		try {
			sprintService.deleteSprint(id);
		} catch (ResourceNotFoundException e) {
			LOG.error("Project with id" + id + " not found");
			return new ResponseEntity<Sprint>(HttpStatus.NOT_FOUND);
		} catch (OperationNotPermitted e) {
			LOG.error("Project with id " + id + " cannot be deleted");
			return new ResponseEntity<Sprint>(HttpStatus.FORBIDDEN);
		}
		LOG.info(ResponseMessage.SUCCESS);
		return new ResponseEntity<Sprint>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/project/{projectId}/sprint", method = RequestMethod.POST, consumes = { "application/json;charset=UTF-8" })
	public ResponseEntity<Object> createSprint(@RequestBody MetaSprint sprint, @PathVariable Integer projectId) {
		LOG.info("A request to POST /project/" + projectId + "/sprint has made");
		try {
		
			sprintService.createSprint(sprint, projectId);
		} catch (ProjectNotFoundException e) {
			LOG.error(ResponseMessage.RESOURCE_NOT_FOUND);
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOG.error(ResponseMessage.INTERNAL_ERROR);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info(ResponseMessage.SUCCESS);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
