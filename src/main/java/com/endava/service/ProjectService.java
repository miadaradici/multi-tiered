package com.endava.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endava.controller.ProjectController;
import com.endava.dao.ProjectDAO;
import com.endava.dao.UserDAO;
import com.endava.exceptions.OperationNotPermitted;
import com.endava.exceptions.ProjectNotFoundException;
import com.endava.exceptions.UserNotFoundException;
import com.endava.model.JobTitle;
import com.endava.model.MetaProject;
import com.endava.model.MetaUser;
import com.endava.model.Project;
import com.endava.model.User;

@Service
public class ProjectService {

	@Autowired
	ProjectDAO projectDAO;

	@Autowired
	UserDAO userDAO;

	public static final Logger LOG = Logger.getLogger(ProjectService.class);

	public Project getProjectById(Integer id) throws ProjectNotFoundException {
		Project project = projectDAO.getProject(id);
		return project;
	}

	public ArrayList<Project> getProjects() {
		ArrayList<Project> project = new ArrayList<Project>();
		project = projectDAO.getProjects();
		return project;
	}

	public void deleteProject(Integer id) throws ProjectNotFoundException {
		LOG.info("Deleting the project with id = " + id);
		Project project = projectDAO.getProject(id);
		try {
			projectDAO.delete(project);
		} catch (Exception e) {
			throw new OperationNotPermitted();
		}
	}

	public void createProject(MetaProject metaProject) throws Exception {
		if (metaProject.getScrumMaster() != null) {
			User sm = metaProject.getScrumMaster();
			if (userDAO.getUser(metaProject.getScrumMaster().getId()) == null) {
				LOG.info("Scrumaster doesn't exist.");
				throw new UserNotFoundException();
			}
		}
		if (metaProject.getId() != null) {
			LOG.info("Will update project");
			Project project = projectDAO.getProject(metaProject.getId());
			project.setScrumMaster(userDAO.findUser(metaProject.getScrumMaster().getName(),
					metaProject.getScrumMaster().getSurname()));
			project.setName(metaProject.getName());
			projectDAO.update(project);
		} else {
			LOG.info("Will create project");
			Project project = new Project(metaProject);
			project.setScrumMaster(userDAO.getUser(metaProject.getScrumMaster().getId()));
			projectDAO.save(project);
		}
	}

	public void saveOrUpdate(Project project) throws Exception {
		projectDAO.save(project);
	}
}
