package com.multitired.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multitired.dao.CeremonyDAO;
import com.multitired.dao.ProjectDAO;
import com.multitired.dao.SprintDAO;
import com.multitired.exceptions.CeremonyNotFoundException;
import com.multitired.exceptions.OperationNotPermitted;
import com.multitired.exceptions.ProjectNotFoundException;
import com.multitired.exceptions.SprintNotFoundException;
import com.multitired.exceptions.UserNotFoundException;
import com.multitired.model.Ceremony;
import com.multitired.model.CeremonyType;
import com.multitired.model.MetaCeremony;
import com.multitired.model.MetaSprint;
import com.multitired.model.Project;
import com.multitired.model.Sprint;
import com.multitired.model.User;

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
		ceremony.setSprint(sprint);
		ceremony.setDate(metaCeremony.getDate());
		ceremony.setDescription(metaCeremony.getDescription());
		ceremony.setType(metaCeremony.getCeremonyType());
		ceremonyDAO.save(ceremony);
		LOG.info("Ceremony was created");
	}
	
	public void saveOrUpdate(Ceremony ceremony) throws Exception {
		ceremonyDAO.save(ceremony);
	}

}
