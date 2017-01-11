package com.multitired.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.multitired.exceptions.CeremonyNotFoundException;
import com.multitired.exceptions.UserNotFoundException;
import com.multitired.model.Ceremony;
import com.multitired.model.Project;
import com.multitired.model.Sprint;
import com.multitired.model.User;
import com.multitired.service.ProjectService;

@Component
public class CeremonyDAO {

	@PersistenceContext
	private EntityManager em;

	private static final Logger LOG = LoggerFactory.getLogger(CeremonyDAO.class);
	
	@Transactional
	public Ceremony getCeremony(Integer id) throws CeremonyNotFoundException {
		LOG.info("Retreiving ceremony with id " + id);
		Ceremony ceremony = (Ceremony) em.find(Ceremony.class, id);
		if (ceremony != null) {
			return ceremony;
		}
		throw new CeremonyNotFoundException();
	}
	
	@Transactional
	public void delete(Ceremony ceremony) {
		LOG.info("Deleting ceremony with id " + ceremony.getId());
		em.remove(em.merge(ceremony));
		LOG.info("Ceremony with id" + ceremony.getId() + " was deleted");
	}
	
	@Transactional
	public List<Ceremony> getSprintsCeremonies(Sprint sprint, Integer sprintId){
		LOG.info("Retreiving all sprint's ceremonies from database");
		List<Ceremony> ceremonies = em.createNamedQuery(Ceremony.FIND_ALL_SPRINTS_CEREMONIES).setParameter("sprint", sprint).getResultList();
		if (ceremonies != null && ceremonies.size()>=1) {
			LOG.info("Found sprint's ceremonies for sprin with id = " + sprintId);
			return ceremonies;
		}
		LOG.info("No sprint's ceremonies for sprint with id = " + sprintId + " was found");
		return null;
	}
	
	@Transactional
	public void save(Ceremony ceremony) throws Exception {
		LOG.info("Saving ceremony");
		em.merge(ceremony);
		LOG.info("Ceremony was saved");
	}

}
