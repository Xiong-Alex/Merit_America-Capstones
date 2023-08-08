package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.Email;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.User;

@Service
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
    }

    @Override
    public User getUserById(Long userId) {
        String sql = "SELECT * FROM users JOIN emails ON emails.email_id = users.email_id WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToUser(results);
        } else {
            throw new RuntimeException("userId " + userId + " was not found.");
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users JOIN emails ON emails.email_id = users.email_id";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if (user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String email, String password, String role) {
        boolean userCreated = false;

        // create email
        int emailId = createEmail(email);

        // create user
        String insertUser = "insert into users (username,password_hash,role,email_id) values(?,?,?,?)";
        
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertUser, new String[] { id_column });
            ps.setString(1, username);
            ps.setString(2, password_hash);
            ps.setString(3, ssRole);
            ps.setInt(4, emailId);
            return ps;
        }, keyHolder) == 1;

        int newUserId = (int) keyHolder.getKeys().get(id_column);

        return userCreated;
    }

    private int createEmail(String email) {
        String insertEmail = "INSERT INTO emails (email) VALUES (?)";

        GeneratedKeyHolder emailKeyHolder = new GeneratedKeyHolder();
        String email_id_column = "email_id";

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertEmail, new String[] { email_id_column });
            ps.setString(1, email);
            return ps;
        }, emailKeyHolder);

        int emailId = (int) emailKeyHolder.getKeys().get(email_id_column);
        return emailId;
    }

    @Override
    public Email saveEmail(String emailStr) {
        // Find if email exists
        String sql = "SELECT email_id FROM emails WHERE email = ?;";
        Integer emailId = null;

        try {
            emailId = jdbcTemplate.queryForObject(sql, Integer.class, emailStr);
        } catch (EmptyResultDataAccessException e) {
            emailId = createEmail(emailStr);
        }

        Email email = new Email();
        email.setEmailId(emailId);
        email.setEmail(emailStr);

        return email;
    }

    @Override
    public Email findEmailByUserName(String userName) {
        String sql = "SELECT e.email_id, e.email FROM emails as e " +
                "JOIN users as u ON  u.email_id = e.email_id " +
                "WHERE u.username = ?; ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userName);
        Email email = null;
        if(result.next()){
            email = mapRowToEmail(result);
        }
        return email;
    }

    @Override
    public List<Email> findGuestEmails(Long inviteId) {
        String sql = "SELECT e.email_id as email_id, e.email as email FROM emails as e " +
                "JOIN invite_guest as g ON g.email_id = e.email_id " +
                "WHERE g.invite_id = ? ;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, inviteId);

        List<Email> guestEmails = new ArrayList<>();

        while(results.next()) {
            Email email = mapRowToEmail(results);
            guestEmails.add(email);
        }

        return guestEmails;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(rs.getString("role"));
        user.setEmail(rs.getString("email"));
        user.setActivated(true);
        return user;
    }

    private Email mapRowToEmail(SqlRowSet rs){
        Email email = new Email();
        email.setEmailId(rs.getInt("email_id"));
        email.setEmail(rs.getString("email"));
        return email;
    }
}
