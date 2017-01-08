package com.endava.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.endava.exceptions.ResourceNotFoundException;
import com.endava.exceptions.UserNotFoundException;
import com.endava.model.Project;
import com.endava.model.Sprint;
import com.endava.model.User;
import java.util.Date;


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
		LOG.info("Sprint with id " + id+ " dosen't exist in db");
		throw new ResourceNotFoundException();
	}
	
	@Transactional
	public void delete(Sprint sprint) {
		LOG.info("Deleting sprint with id " + sprint.getId());
		em.remove(em.merge(sprint));
		LOG.info("User with id" + sprint.getId() + " was deleted");
	}
	
	@Transactional
	public List<Sprint> getProjectsSprints(Project project, Integer projectId){
		LOG.info("Retreiving all projects sprints from database");
		List<Sprint> sprints= em.createNamedQuery(Sprint.FIND_ALL_PROJECTS_SPRINTS).setParameter("project", project).getResultList();
		if (sprints != null && sprints.size()>=1) {
			LOG.info("Found projects sprints for project with id = " + projectId);
			return sprints;
		}
		LOG.info("No projects sprints for project with id = " + projectId + " was found");
		return null;
	}
	
	@Transactional
	public Sprint findSprint(Project project, Date startDate, Date endDate ) {
		LOG.info("Finding sprint with start date = " + startDate + " and end date = " + endDate);
		List<Sprint> sprints = em.createNamedQuery(Sprint.FIND_SPRINT_WITH_DATES).setParameter("startDate", startDate).setParameter("endDate", endDate).setParameter("project", project).getResultList();
		if (sprints != null && sprints.size()>=1) {
			LOG.info("Found an sprint with start date = " + startDate + " and end date = " + endDate);
			return sprints.get(0);
		}
		LOG.info("No sprint with start date = " + startDate + " was found");
		return null;
	}

	@Transactional
	public void save(Sprint sprint) throws Exception {
		LOG.info("Saving sprint");
		em.merge(sprint);
		LOG.info("Sprint was saved");
	}

}
