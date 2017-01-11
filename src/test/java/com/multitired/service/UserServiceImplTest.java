package com.multitired.service;
/*package com.endava.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.endava.com.endava.util.GenericDao;
import com.endava.dao.UserDao;
import com.endava.exception.UserAlreadyExistsException;
import com.endava.exception.UserNotFoundException;
import com.endava.model.User;
import com.endava.service.impl.UserServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:hs.xml",
        "classpath:root-context.xml"})
@Transactional
public class UserServiceImplTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Autowired
    private GenericDao genericDao;

    private List<User> users;

    @Before
    public void setup() {
//        userService = new UserServiceImpl();
//
//        users = new ArrayList<User>();
//        for (int i = 1; i <= 10; i++) {
//            User user = new User();
//            user.setName("Nume" + i);
//
//            users.add(user);
//            genericDao.persist(user);
//        }
//
//        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
//        genericDao.deleteAll(users);
//        users.clear();
    }

    @Test
    public void shouldGetUserWithId() throws UserNotFoundException {
//        when(userDao.getUser(2L)).thenReturn(users.get(2));
//
//        User user = userService.getUser(2L);
//        assertEquals(users.get(2), user);
//    
    }
    @Test
    public void shouldSaveUser() throws UserAlreadyExistsException {
//        User user = new User();
//        user.setName("Name 20");
//        userService.saveUser(user);
//
//        verify(userDao).saveUser(user);

    }
}
*/