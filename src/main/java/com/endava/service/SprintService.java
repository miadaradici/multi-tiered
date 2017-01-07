package com.endava.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endava.dao.SprintDAO;
import com.endava.dao.UserDAO;
import com.endava.exceptions.OperationNotPermitted;
import com.endava.exceptions.ProjectNotFoundException;
import com.endava.exceptions.ResourceNotFoundException;
import com.endava.model.Project;
import com.endava.model.Sprint;

@Service
public class SprintService {

	@Autowired
	SprintDAO sprintDAO;

	private static final Logger LOG = Logger.getLogger(SprintService.class);

	public Sprint getSprintById(Integer id) throws ResourceNotFoundException{
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
}
