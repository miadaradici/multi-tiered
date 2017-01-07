package com.endava.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.endava.controller.HomeController;
import com.endava.controller.UserController;
import com.endava.dao.UserDAO;
import com.endava.exceptions.OperationNotPermitted;
import com.endava.exceptions.UserNotFoundException;
import com.endava.model.JobTitle;
import com.endava.model.MetaUser;
import com.endava.model.User;

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
		if (userDAO.findUser(metaUser.getName(), metaUser.getSurname()) != null) {
			LOG.info("User already exists. Will update");
			User user = userDAO.findUser(metaUser.getName(), metaUser.getSurname());
			user.setUsername(metaUser.getUsername());
			user.setEmail(metaUser.getEmail());
			user.setJobTitle(JobTitle.valueOf(metaUser.getJobTitle()));
			user.setPassword(metaUser.getPassword());
			user.setRole(metaUser.getRole());
			userDAO.save(user);
		} else {
			LOG.info("User doesn't exists. Will create");
			User user = new User(metaUser);
			userDAO.save(user);
		}
	}
}
