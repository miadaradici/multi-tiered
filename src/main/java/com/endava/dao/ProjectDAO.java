package com.endava.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.endava.exceptions.ProjectNotFoundException;
import com.endava.exceptions.UserNotFoundException;
import com.endava.model.Project;
import com.endava.model.User;
import com.endava.service.ProjectService;

@Component
public class ProjectDAO {

	@PersistenceContext
	private EntityManager em;

	private static final Logger LOG = LoggerFactory.getLogger(ProjectDAO.class);

	public ProjectDAO() {
	}

	@Transactional
	public Project getProject(Integer id) throws ProjectNotFoundException {
		LOG.info("Retreiving project with id " + id);
		Project project = (Project) em.find(Project.class, id);
		if (project != null) {
			project.getParticipants();
			return project;
		}
		throw new ProjectNotFoundException();
	}

	@Transactional
	public ArrayList<Project> getProjects() {
		LOG.info("Retreiving all projects from database");
		Query q = (Query) em.createNamedQuery(Project.FIND_ALL);
		return (ArrayList<Project>) q.getResultList();
	}

	@Transactional
	public void delete(Project project) {
		LOG.info("Deleting project with id " + project.getId());
		em.remove(em.merge(project));
		LOG.info("User with id" + project.getId() + " was deleted");
	}

	@Transactional
	public Project findProject(String name) {
		LOG.info("Finding project with name = " + name);
		List<Project> projects = em.createNamedQuery(Project.FIND_PROJECT_WITH_NAME).setParameter("name", name)
				.getResultList();
		if (projects != null && projects.size() >= 1) {
			LOG.info("Found an project with the name = " + name);
			return projects.get(0);
		}
		LOG.info("No project with name = " + name + " was found");
		return null;
	}

	@Transactional
	public void save(Project project) throws Exception {
		LOG.info("Saving project");
		if (project.getId() == null) {
			em.persist(project);
		} else {
			em.merge(project);
		}
		LOG.info("Project was saved");
	}

	@Transactional
	public void update(Project project) {
		em.merge(project);
	}
}
