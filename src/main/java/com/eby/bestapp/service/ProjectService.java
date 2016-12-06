package com.eby.bestapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eby.bestapp.dao.ProjectDAO;
import com.eby.bestapp.model.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectDAO projectDAO;
	
	public Project getProjectById(Integer id){
		Project project = projectDAO.get(id);
		System.out.println("Project: " + project.getName());
		return project;
	}
	
	public void deleteProjectById(Integer id){
		Boolean deleted = projectDAO.deleteProjectById(id);
	}
}
