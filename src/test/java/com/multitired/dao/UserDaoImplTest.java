package com.multitired.dao;
/*package com.endava.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.endava.com.endava.util.GenericDao;
import com.endava.exception.UserAlreadyExistsException;
import com.endava.exception.UserNotFoundException;
import com.endava.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:hs.xml",
        "classpath:root-context.xml"})
@Transactional
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private GenericDao genericDao;

    private List<User> users;

//    @Before
//    public void setUp() throws Exception {
//        users = new ArrayList<User>();
//}
//
//    @After
//    public void tearDown() throws Exception {
//        genericDao.deleteAll(users);
//        users.clear();
//    }

    @Test
    public void shouldSaveUser() throws UserAlreadyExistsException {
//        User user = new User();
//        user.setName("Jon Snow");
//
//        userDao.saveUser(user);
//
//        assertNotNull(user.getId());
//        assertEquals("Jon Snow", user.getName());
    }

  //  @Test(expected = UserNotFoundException.class)
    public void shouldThrowExceptionForUserNotFound() throws UserNotFoundException {
//        userDao.getUser(1L);
    }

    @Test
    public void shouldGetUserForId() throws UserNotFoundException {
//        User user = new User();
//        user.setName("Jon Snow");
//        genericDao.persist(user);
//
//        User getUser = userDao.getUser(1L);
//        assertEquals(user.getId(), getUser.getId());
    }
}
*/