package com.endava.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endava.dao.ProjectDAO;
import com.endava.dao.SprintDAO;
import com.endava.dao.UserDAO;
import com.endava.exceptions.OperationNotPermitted;
import com.endava.exceptions.ProjectNotFoundException;
import com.endava.exceptions.ResourceNotFoundException;
import com.endava.exceptions.SprintNotFoundException;
import com.endava.exceptions.UserNotFoundException;
import com.endava.model.MetaProject;
import com.endava.model.MetaSprint;
import com.endava.model.Project;
import com.endava.model.Sprint;
import com.endava.model.User;

@Service
public class SprintService {

	@Autowired
	SprintDAO sprintDAO;

	@Autowired
	ProjectDAO projectDAO;

	private static final Logger LOG = Logger.getLogger(SprintService.class);

	public Sprint getSprintById(Integer id) throws ResourceNotFoundException {
		Sprint sprint = sprintDAO.getSprint(id);
		return sprint;
	}

	public void deleteSprint(Integer id) throws ResourceNotFoundException {
		LOG.info("Deleting the sprint with id = " + id);
		Sprint sprint = sprintDAO.getSprint(id);
		try {
			sprintDAO.delete(sprint);
		} catch (Exception e) {
			throw new OperationNotPermitted();
		}
	}

	public List<Sprint> getProjectsSprints(Integer projectId) throws ProjectNotFoundException, SprintNotFoundException {
		Project project = projectDAO.getProject(projectId);
		if (project != null) {
			List<Sprint> sprints = sprintDAO.getProjectsSprints(project, projectId);
			if (sprints != null) {
				return sprints;
			}
			throw new SprintNotFoundException();
		}
		throw new ProjectNotFoundException();
	}

	public void createSprint(MetaSprint metaSprint, Integer projectId) throws ProjectNotFoundException, Exception {
		Project project = projectDAO.getProject(projectId);
		if (project == null) {
			LOG.info("Project doesn't exists.");
			throw new ProjectNotFoundException();
		}

		if (sprintDAO.findSprint(project, metaSprint.getStartDate(), metaSprint.getEndDate()) != null) {
			LOG.info("Sprint already exists. Will update capacity");
			Sprint sprint = sprintDAO.findSprint(project, metaSprint.getStartDate(), metaSprint.getEndDate());
			sprint.setCapacity(metaSprint.getCapacity());
			sprintDAO.save(sprint);
		} else {
			LOG.info("Sprint doesn't exists. Will create");
			Sprint sprint = new Sprint(metaSprint);
			sprint.setProject(project);
			sprintDAO.save(sprint);
		}
	}
}
