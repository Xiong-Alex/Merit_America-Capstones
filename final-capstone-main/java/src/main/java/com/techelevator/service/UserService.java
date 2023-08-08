package com.techelevator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public List<User> getAllUser(String loggedInUser) {
        List<User> users = userDao.findAll();
        users.removeIf(user -> user.getUsername().equals(loggedInUser));
        return users;
    }
}
