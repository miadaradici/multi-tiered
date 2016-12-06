package com.eby.bestapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eby.bestapp.controller.HomeController;
import com.eby.bestapp.dao.UserDAO;
import com.eby.bestapp.model.User;

@Service
public class UserService {

	@Autowired
	UserDAO userDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public User getUserById(Integer id){
		User u = userDAO.get(id);
		System.out.println("User: " + u.getName() + " " + u.getSurname());
		return u;
	}
	
	public void deleteUserById(Integer id){
		Boolean deleted = userDAO.deleteUserById(id);
	}
	
	public void createUser(User u){
		userDAO.createUser(u);
	}
	
	public void updateUser(User u){
		userDAO.updateUser(u);
	}
	
}
