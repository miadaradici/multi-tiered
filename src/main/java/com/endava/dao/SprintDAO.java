package com.endava.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.endava.exceptions.ResourceNotFoundException;
import com.endava.exceptions.UserNotFoundException;
import com.endava.model.Sprint;
import com.endava.model.User;

@Repository
@EnableTransactionManagement
public class SprintDAO {

	@PersistenceContext
	private EntityManager em;

	private static final Logger LOG = Logger.getLogger(SprintDAO.class);
	
	@Transactional
	public Sprint getSprint(Integer id) throws ResourceNotFoundException {
		LOG.info("Retreiving sprint with id " + id);
		Sprint sprint = (Sprint) em.find(Sprint.class, id);
		if (sprint != null) {
			return sprint;
		}
		LOG.info("Sprint with id " + id+ "dosen't exits in db");
		throw new ResourceNotFoundException();
	}
	
	@Transactional
	public void delete(Sprint sprint) {
		LOG.info("Deleting sprint with id " + sprint.getId());
		em.remove(em.merge(sprint));
		LOG.info("User with id" + sprint.getId() + " was deleted");
	}

}
