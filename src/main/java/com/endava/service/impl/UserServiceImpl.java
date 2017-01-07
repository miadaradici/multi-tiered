////package com.endava.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.endava.dao.UserDao;
//import com.endava.exception.UserAlreadyExistsException;
//import com.endava.exception.UserNotFoundException;
//import com.endava.model.User;
//import com.endava.service.UserService;
//
///**
// * Created by lfilote on 5/16/2016.
// */
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    UserDao userDao;
//
//    @Override
//    public User getUser(Long id) throws UserNotFoundException {
//        return userDao.getUser(id);
//
//    }
//
//    @Override
//    public void saveUser(User user) throws UserAlreadyExistsException {
//        userDao.saveUser(user);
//    }
//}
