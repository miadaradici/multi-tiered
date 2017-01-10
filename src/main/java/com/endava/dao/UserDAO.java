package com.endava.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.endava.exceptions.UserNotFoundException;
import com.endava.model.User;
import com.endava.service.UserService;

import javax.persistence.Query;

@Repository
@EnableTransactionManagement
public class UserDAO {

	@PersistenceContext
	private EntityManager em;

	private static final Logger LOG = Logger.getLogger(UserDAO.class);

	public UserDAO() {
	}

	@Transactional
	public User getUser(Integer id) throws UserNotFoundException {
		LOG.info("Retreiving user with id " + id);
		User user = (User) em.find(User.class, id);
		if (user != null) {
			return user;
		}
		throw new UserNotFoundException();
	}

	@Transactional
	public ArrayList<User> getUsers() {
		LOG.info("Retreiving all users from database");
		Query q = (Query) em.createNamedQuery(User.FIND_ALL);
		return (ArrayList<User>) q.getResultList();
	}

	@Transactional
	public void delete(User user) {
		LOG.info("Deleting user with id " + user.getId());
		em.remove(em.merge(user));
		LOG.info("User with id" + user.getId() + " was deleted");
	}

	@Transactional
	public void save(User user) throws Exception {
		LOG.info("Saving user");
		em.persist(user);
		LOG.info("User was saved");
	}

	@Transactional
	public User findUser(String name, String surname) {
		LOG.info("Finding user with name = " + name + " and username " + surname);
		List<User> users = em.createNamedQuery(User.FIND_USER_WITH_NAME).setParameter("userName", name)
				.setParameter("userSurname", surname).getResultList();
		if (users != null && users.size() >= 1) {
			LOG.info("Found an user with the name = " + name);
			return users.get(0);
		}
		LOG.info("No user with name = " + name + " was found");
		return null;
	}

	@Transactional
	public void update(User user) {
		em.merge(user);
	}

	//
	// public boolean deleteUserById(Integer id){
	// Session session = sessionFactory.openSession();
	// User u = (User) session.get(User.class, id);
	//
	// try {
	// session.delete(u);
	// session.flush();
	// return true;
	// }
	// catch (Exception e){
	// System.out.println("Userul nu poate fi sters deoarece este scrum
	// master");
	// return false;
	// }
	// }
	//
	// public void createUser(User u){
	// Session session = sessionFactory.openSession();
	// session.save(u);
	// }
	//
	// public void updateUser(User u){
	// Session session = sessionFactory.openSession();
	// session.update(u);
	// }
}
