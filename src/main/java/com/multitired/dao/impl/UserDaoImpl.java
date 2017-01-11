package com.multitired.dao.impl;
/*package com.endava.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.endava.dao.UserDao;
import com.endava.exception.UserAlreadyExistsException;
import com.endava.exception.UserNotFoundException;
import com.endava.model.User;


@Repository
@EnableTransactionManagement
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User getUser(Long id) throws UserNotFoundException {
        User user = (User) em.find(User.class, id);
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException();
    }

    @Override
    public void saveUser(User user) throws UserAlreadyExistsException {
        if (user.getId() != null) {
            throw new UserAlreadyExistsException();
        }
        em.persist(user);
    }
}
*/