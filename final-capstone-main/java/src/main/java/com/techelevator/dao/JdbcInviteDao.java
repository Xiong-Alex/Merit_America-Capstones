package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcInviteDao implements InviteDao{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserDao userDao;
    public JdbcInviteDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public Invite findById(Long inviteId) {
        String sql = "SELECT * FROM invite WHERE invite_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, inviteId);
        Invite invite =  null;
        if(results.next()){
            invite = mapRowToInvite(results);
        }
        return invite;
    }

    @Override
    public Invite save(Invite invite) {
        String sql = "INSERT INTO invite (user_id, decision_by, message) VALUES (?, ?, ?) RETURNING invite_id;";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, invite.getCreatedBy().getId() , invite.getDecisionBy(),
                invite.getMessage());
        invite.setInviteId(id);
        return invite;
    }

    @Override
    public boolean saveToInvitedRestaurant(Long restaurantId, Long inviteId) {
        String sql = "INSERT INTO invite_restaurant (invite_id, restaurant_id) VALUES (?, ?);";
        int rows = jdbcTemplate.update(sql, inviteId, restaurantId);
        return rows == 1;
    }

    @Override
    public boolean saveInviteGuest(Long inviteId, Integer emailId) {
        String sql = "INSERT INTO invite_guest (invite_id, email_id) VALUES (?, ?);";
        int rows = jdbcTemplate.update(sql, inviteId, emailId);
        return rows == 1;
    }

    @Override
    public List<Invite> findByCreatedBy(Long createdById) {
        String sql = "SELECT * FROM invite  WHERE user_id = ?;";
        SqlRowSet  results = jdbcTemplate.queryForRowSet(sql, createdById);

        List<Invite> invites = new ArrayList<>();

        while(results.next()){
            Invite invite = mapRowToInvite(results);
            invites.add(invite);
        }


        return invites;
    }

    @Override
    public List<Invite> findByEmailId(Long emailId) {
        String sql = "SELECT * FROM invite as i JOIN invite_guest as g ON g.invite_id = i.invite_id " +
                "WHERE email_id = ? ; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, emailId);

        List<Invite> invites = new ArrayList<>();
        while (results.next()){
            Invite invite = mapRowToInvite(results);
            invites.add(invite);
        }
        return invites;
    }

    @Override
    public List<Guest> findGuests(Long inviteId) {
        String sql = "SELECT e.email_id, e.email FROM emails as e " +
                "JOIN invite_guest as g ON g.email_id = e.email_id " +
                "WHERE g.invite_id = ?;";

        List<Guest> results = jdbcTemplate.query(sql, new Object[] {inviteId},  (rs, rowNum) -> {
            Email email = new Email();
            email.setEmailId(rs.getInt("email_id"));
            email.setEmail(rs.getString("email"));

            Guest guest =  new Guest();
            guest.setInviteId(inviteId);
            guest.setEmail(email);

            return guest;
        });

        return results;
    }

    private Invite mapRowToInvite(SqlRowSet rs) {
        Invite invite = new Invite();
        invite.setInviteId(rs.getLong("invite_id"));
        invite.setDecisionBy(rs.getDate("decision_by").toLocalDate());
        invite.setMessage(rs.getString("message"));
        invite.setCreatedBy(userDao.getUserById(rs.getLong("user_id")));
        return invite;
    }
}
