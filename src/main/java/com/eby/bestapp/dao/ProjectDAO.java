package com.eby.bestapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eby.bestapp.model.Project;
import com.eby.bestapp.model.User;

@Component
public class ProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public ProjectDAO() {
	}

	public ProjectDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public Project get(Integer id) {
		Session session = sessionFactory.openSession();
		return (Project) session.get(Project.class, id);
	}
	
	public boolean deleteProjectById(Integer id){
		Session session = sessionFactory.openSession();
		Project project = (Project) session.get(Project.class, id);
		
		try {
			session.delete(project);
			session.flush();
			return true;
		}
		catch (Exception e){
			System.out.println("Userul nu poate fi sters");
			return false;
		}
	}
	
}
