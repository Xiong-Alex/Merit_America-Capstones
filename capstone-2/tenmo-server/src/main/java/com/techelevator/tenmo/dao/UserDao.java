package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.List;

public interface UserDao {

    List<User> findAll();

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password);

    Long getUserId(String username) throws UsernameNotFoundException;

    List<User> findUsersNotYou(Long userId);

    public String findUsernameById(Long userId);
}
