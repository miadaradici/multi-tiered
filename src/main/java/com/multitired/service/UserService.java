package com.multitired.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.multitired.controller.HomeController;
import com.multitired.controller.UserController;
import com.multitired.dao.UserDAO;
import com.multitired.exceptions.OperationNotPermitted;
import com.multitired.exceptions.UserNotFoundException;
import com.multitired.model.JobTitle;
import com.multitired.model.MetaUser;
import com.multitired.model.User;

@Service
public class UserService {

	@Autowired
	UserDAO userDAO;

	private static final Logger LOG = Logger.getLogger(UserService.class);

	public User getUserById(Integer id) throws UserNotFoundException {
		User u = userDAO.getUser(id);
		return u;
	}

	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		users = userDAO.getUsers();
		return users;
	}

	public void deleteUser(Integer id) throws UserNotFoundException {
		LOG.info("Deleting the user with id = " + id);
		User article = userDAO.getUser(id);
		try {
			userDAO.delete(article);
		} catch (Exception e) {
			throw new OperationNotPermitted();
		}
	}

	public void createUser(MetaUser metaUser) throws Exception {
		if (metaUser.getId() != null) {
			LOG.info("User with id " + metaUser.getId() + " will be updated");
			User user = userDAO.getUser(metaUser.getId());
			user.setName(metaUser.getName());
			user.setSurname(metaUser.getSurname());
			user.setUsername(metaUser.getUsername());
			user.setEmail(metaUser.getEmail());
			user.setJobTitle(JobTitle.valueOf(metaUser.getJobTitle()));
			user.setPassword(metaUser.getPassword());
			user.setRole(metaUser.getRole());
			userDAO.update(user);
		} else {
			LOG.info("User will be created");
			User user = new User(metaUser);
			userDAO.save(user);
		}
	}
}
