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
import org.springframework.web.bind.annotation.ResponseBody;

import com.endava.exceptions.CeremonyNotFoundException;
import com.endava.exceptions.OperationNotPermitted;
import com.endava.exceptions.ProjectNotFoundException;
import com.endava.exceptions.ResourceNotFoundException;
import com.endava.exceptions.SprintNotFoundException;
import com.endava.exceptions.UserNotFoundException;
import com.endava.model.Ceremony;
import com.endava.model.MetaCeremony;
import com.endava.model.Project;
import com.endava.model.User;
import com.endava.service.CeremonyService;
import com.endava.service.UserService;
import com.endava.utils.ResponseMessage;

@Controller
public class CeremonyController {

	@Autowired
	CeremonyService ceremonyService;
	
	@Autowired
	UserService userService;

	public static final Logger LOG = Logger.getLogger(CeremonyController.class);

	@RequestMapping(value = "/ceremony/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ceremony> getCeremony(@PathVariable Integer id) {
		LOG.info("A request to GET ceremony/" + id + " has made");
		try {
			Ceremony ceremony = ceremonyService.getCeremonyById(id);
			return new ResponseEntity<Ceremony>(ceremony, HttpStatus.OK);
		} catch (CeremonyNotFoundException e) {
			LOG.error("Ceremony with id" + id + "not found");
		}
		return new ResponseEntity<Ceremony>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/ceremony/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Ceremony> deleteCeremony(@PathVariable Integer id) {
		LOG.info("A request to DELETE ceremony/" + id + " has made");
		try {
			ceremonyService.deleteCeremony(id);
		} catch (CeremonyNotFoundException e) {
			LOG.error("Ceremony with id" + id + " not found");
			return new ResponseEntity<Ceremony>(HttpStatus.NOT_FOUND);
		} catch (OperationNotPermitted e) {
			LOG.error("Ceremony with id " + id + " cannot be deleted");
			return new ResponseEntity<Ceremony>(HttpStatus.FORBIDDEN);
		}
		LOG.info(ResponseMessage.SUCCESS);
		return new ResponseEntity<Ceremony>(HttpStatus.OK);
	}

	@RequestMapping(value = "/sprint/{sprintId}/ceremonies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<Ceremony> getCeremonies(@PathVariable Integer sprintId) {
		LOG.info("A request to GET sprint/" + sprintId + "/ceremonies has made");
		try {
			Collection<Ceremony> ceremonies = ceremonyService.getSprintsCeremonies(sprintId);
			LOG.info("Returning ceremonies of sprint with id " + sprintId);
			return ceremonies;
		} catch (SprintNotFoundException e) {
			LOG.error("Sprint with id" + sprintId + "not found");
		} catch (CeremonyNotFoundException e) {
			LOG.error("Ceremonies of sprint with id" + sprintId + "not found");
		}
		throw new ResourceNotFoundException();
	}

	@RequestMapping(value = "/sprint/{sprintId}/ceremony", method = RequestMethod.POST, consumes = {
			"application/json;charset=UTF-8" })
	public ResponseEntity<Object> createCeremony(@RequestBody MetaCeremony ceremony, @PathVariable Integer sprintId) {
		LOG.info("A request to POST /sprint/" + sprintId + "/ceremony has made");
		try {
			ceremonyService.createCeremony(ceremony, sprintId);

		} catch (ResourceNotFoundException e) {
			LOG.error(ResponseMessage.RESOURCE_NOT_FOUND);
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		} catch (SprintNotFoundException e) {
			LOG.error(ResponseMessage.RESOURCE_NOT_FOUND);
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			LOG.error(ResponseMessage.INTERNAL_ERROR);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info(ResponseMessage.SUCCESS);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/ceremony/{ceremonyId}/user/{userId}", method = RequestMethod.POST, consumes = {
			"application/json;charset=UTF-8" })
	public ResponseEntity<Object> addUserToCeremony(@PathVariable Integer ceremonyId, @PathVariable Integer userId) {
		LOG.info("A request to POST /ceremony/user was made");
		try {
			Ceremony ceremony = ceremonyService.getCeremonyById(ceremonyId);
			User user = userService.getUserById(userId);
			ceremony.getCeremonyParticipants().add(user);
			ceremonyService.saveOrUpdate(ceremony);
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
