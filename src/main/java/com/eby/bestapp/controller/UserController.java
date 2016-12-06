package com.eby.bestapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eby.bestapp.dao.UserDAO;
import com.eby.bestapp.model.User;
import com.eby.bestapp.service.ProjectService;
import com.eby.bestapp.service.UserService;


@Controller
@RequestMapping({"/user"})
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public String deleteUser(@RequestParam Integer userId) {
		userService.deleteUserById(userId);
        return "home";
    }
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser(@RequestParam Integer userId) {
		User u = userService.getUserById(userId);
        return "home";
    }
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String createUser(@RequestParam User user) {
		userService.createUser(user);
        return "home";
    }
	
	@RequestMapping(value = "/modifyUser", method = RequestMethod.PUT)
    public String updateUser(@RequestParam User user) {
		userService.updateUser(user);
        return "home";
    }
	
}
