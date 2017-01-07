package com.endava.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.endava.exceptions.OperationNotPermitted;
import com.endava.exceptions.ResourceNotFoundException;
import com.endava.exceptions.UserNotFoundException;
import com.endava.model.MetaUser;
import com.endava.model.User;
import com.endava.service.UserService;
import com.endava.utils.ResponseMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@Controller
public class UserController {

	@Autowired
	UserService userService;

	public static final Logger LOG = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<User> deleteUser(@PathVariable Integer id) {
		LOG.info("A request to DELETE user/" + id + " has made");
		try {
			userService.deleteUser(id);
		} catch (UserNotFoundException e) {
			LOG.error("User with id" + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} catch (OperationNotPermitted e) {
			LOG.error("User with id " + id + " cannot be deleted");
			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
		}
		LOG.info(ResponseMessage.SUCCESS);
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable Integer id) {
		LOG.info("A request to GET article/" + id + " has made");
		try {
			User user = userService.getUserById(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			LOG.error("User with id" + id + "not found");
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<User> getUsers() {
		Collection<User> users = userService.getUsers();
		System.out.println(users.size());
		if (users == null) {
			LOG.error(ResponseMessage.RESOURCE_NOT_FOUND);
			throw new ResourceNotFoundException();
		}
		LOG.info(ResponseMessage.SUCCESS);
		return users;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST,  consumes = {
	"application/json;charset=UTF-8" })
	public ResponseEntity<Object> createUser(@RequestBody MetaUser user) {
		LOG.info("A request to POST user has made");
		try {
			userService.createUser(user);
		} catch (Exception e) {
			LOG.error(ResponseMessage.INTERNAL_ERROR);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info(ResponseMessage.SUCCESS);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	

//	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
//	public @ResponseBody ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
//		LOG.info("A request to DELETE article/" + id + " has made");
//		try {
//			userService.deleteUser(id);
//			LOG.info(ResponseMessage.SUCCESS);
//			return new ResponseEntity<User>(HttpStatus.OK);
//		} catch (Exception e) {
//			LOG.error(ResponseMessage.INTERNAL_ERROR);
//		}
//		return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	

}
