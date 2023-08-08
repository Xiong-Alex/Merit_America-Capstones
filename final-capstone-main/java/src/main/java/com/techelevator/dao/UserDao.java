package com.techelevator.dao;

import com.techelevator.model.Email;
import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String email, String password, String role);

    Email saveEmail(String emailStr);

    Email findEmailByUserName(String userName);

    List<Email> findGuestEmails(Long inviteId);
}
