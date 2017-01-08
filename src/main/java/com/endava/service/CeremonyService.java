package com.endava.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endava.dao.CeremonyDAO;
import com.endava.dao.ProjectDAO;
import com.endava.dao.SprintDAO;
import com.endava.exceptions.CeremonyNotFoundException;
import com.endava.exceptions.OperationNotPermitted;
import com.endava.exceptions.ProjectNotFoundException;
import com.endava.exceptions.SprintNotFoundException;
import com.endava.exceptions.UserNotFoundException;
import com.endava.model.Ceremony;
import com.endava.model.MetaCeremony;
import com.endava.model.MetaSprint;
import com.endava.model.Project;
import com.endava.model.Sprint;
import com.endava.model.User;

@Service
public class CeremonyService {

	public static final Logger LOG = Logger.getLogger(CeremonyService.class);

	@Autowired
	CeremonyDAO ceremonyDAO;

	@Autowired
	SprintDAO sprintDAO;

	public Ceremony getCeremonyById(Integer id) throws CeremonyNotFoundException {
		Ceremony c = ceremonyDAO.getCeremony(id);
		return c;
	}

	public void deleteCeremony(Integer id) throws CeremonyNotFoundException {
		LOG.info("Deleting the ceremony with id = " + id);
		Ceremony ceremony = ceremonyDAO.getCeremony(id);
		try {
			ceremonyDAO.delete(ceremony);
		} catch (Exception e) {
			throw new OperationNotPermitted();
		}
	}

	public List<Ceremony> getSprintsCeremonies(Integer sprintId)
			throws CeremonyNotFoundException, SprintNotFoundException {
		Sprint sprint = sprintDAO.getSprint(sprintId);
		if (sprint != null) {
			List<Ceremony> ceremonies = ceremonyDAO.getSprintsCeremonies(sprint, sprintId);
			if (ceremonies != null) {
				return ceremonies;
			}
			throw new CeremonyNotFoundException();
		}
		throw new SprintNotFoundException();
	}

	public void createCeremony(MetaCeremony metaCeremony, Integer sprintId) throws SprintNotFoundException, Exception {
		Sprint sprint = sprintDAO.getSprint(sprintId);
		if (sprint == null) {
			LOG.info("Sprint doesn't exists.");
			throw new SprintNotFoundException();
		}
		LOG.info("Creating ceremony...");
		Ceremony ceremony = new Ceremony(metaCeremony);
		LOG.info("create");
		ceremony.setSprint(sprint);
		LOG.info("added sprint");
		LOG.info(ceremony.getType());
		ceremonyDAO.save(ceremony);
		LOG.info("Ceremony was created");
	}

}
