package com.endava.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.endava.exceptions.OperationNotPermitted;
import com.endava.exceptions.ProjectNotFoundException;
import com.endava.exceptions.ResourceNotFoundException;
import com.endava.exceptions.UserNotFoundException;
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
	
	// /project/idproject/sprints
	
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
	
//	@RequestMapping(value = "/project/{id}/sprint", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Sprint> getSprints(@PathVariable Integer projectId) {
//		LOG.info("A request to GET sprint/" + projectId + " has made");
//		try {
//			Sprint sprint = sprintService.getSprintById(projectId);
//			System.out.println(sprint.getId() );
//			System.out.println(sprint.getProject());
//			
//			LOG.info("Returning sprint with id " + projectId);
//			return new ResponseEntity<Sprint>(sprint, HttpStatus.OK);
//		} catch (ResourceNotFoundException e) {
//			LOG.error("Sprint with id" + id + "not found");
//		}
//		return new ResponseEntity<Sprint>(HttpStatus.NOT_FOUND);
//	}
//	
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
}
