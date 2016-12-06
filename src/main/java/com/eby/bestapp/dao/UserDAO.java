package com.eby.bestapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eby.bestapp.controller.HomeController;
import com.eby.bestapp.model.User;

@Component
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	public UserDAO() {
	}

	public UserDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public User get(Integer id) {
		Session session = sessionFactory.openSession();
		return (User) session.get(User.class, id);
	}
	
	public boolean deleteUserById(Integer id){
		Session session = sessionFactory.openSession();
		User u = (User) session.get(User.class, id);
		
		try {
			session.delete(u);
			session.flush();
			return true;
		}
		catch (Exception e){
			System.out.println("Userul nu poate fi sters deoarece este scrum master");
			return false;
		}
	}
	
	public void createUser(User u){
		Session session = sessionFactory.openSession();
		session.save(u);
	}
	
	public void updateUser(User u){
		Session session = sessionFactory.openSession();
		session.update(u);
	}
}

