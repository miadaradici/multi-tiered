package com.eby.bestapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eby.bestapp.model.Project;
import com.eby.bestapp.model.User;
import com.eby.bestapp.service.ProjectService;
import com.eby.bestapp.service.UserService;

@Controller
@RequestMapping({"/project"})
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@RequestMapping(value = "/getProject", method = RequestMethod.GET)
    public String getUser(@RequestParam Integer projectId) {
		Project project = projectService.getProjectById(projectId);
        return "home";
    }
	
	@RequestMapping(value = "/deleteProject", method = RequestMethod.DELETE)
    public String deleteProject(@RequestParam Integer projectId) {
		projectService.deleteProjectById(projectId);
        return "home";
    }
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String createUser(@RequestParam Project project) {
//		projectService.createProject(project);
//        return "home";
//    }
//	
//	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
//    public String updateUser(@RequestParam Project project) {
//		projectService.updateProject(project);
//        return "home";
//    }
	
}
