package com.eby.bestapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eby.bestapp.dao.ProjectDAO;
import com.eby.bestapp.model.Project;

@Service
public class ProjectService {

	@Autowired
	ProjectDAO projectDAO;
	
	public void getProject(){
		Project project = projectDAO.get(1);
		System.out.println("Project: " + project.getName());
	}
}
