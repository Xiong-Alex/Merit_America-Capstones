package com.techelevator.service;

import com.techelevator.dao.InviteDao;
import com.techelevator.dao.RestaurantDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InviteService {
    @Autowired
    UserDao userDao;
    @Autowired
    RestaurantDao restaurantDao;
    @Autowired
    InviteDao inviteDao;

    public InviteService(UserDao userDao, InviteDao inviteDao, RestaurantDao restaurantDao) {
        this.userDao = userDao;
        this.inviteDao = inviteDao;
        this.restaurantDao = restaurantDao;
    }

    public Invite addInvite(InviteDTO inviteDto, String createdByUserName) {
        Invite invite = new Invite();
        User createdBy = userDao.findByUsername(createdByUserName);

        invite.setCreatedBy(createdBy);
        invite.setDecisionBy(inviteDto.getDecisionBy());
        invite.setMessage(inviteDto.getMessage());

        List<Restaurant> restaurants = inviteDto.getRestaurantIds().stream()
                .map(id -> restaurantDao.findById(id))
                .collect(Collectors.toList());
        invite.setRestaurants(restaurants);

        // Save to invite table
        inviteDao.save(invite);

        // Save to invite_restaurant table
        restaurants.forEach(restaurant ->
                inviteDao.saveToInvitedRestaurant(restaurant.getRestaurantId(), invite.getInviteId()));

        // Save email table if email does not exist and get the email if it exists
        List<Email> emails = inviteDto.getGuestEmails().stream()
                .map(emailStr -> userDao.saveEmail(emailStr))
                .collect(Collectors.toList());
        invite.setGuestEmails(emails);

        // Save to invite_guest table
        emails.forEach(email -> inviteDao.saveInviteGuest(invite.getInviteId(), email.getEmailId()));

        return invite;
    }

    public List<Invite> getInvites(String createdByUserName) {
        User createdBy = userDao.findByUsername(createdByUserName);
        List<Invite> invites = inviteDao.findByCreatedBy(createdBy.getId());

        invites.forEach(invite -> {
            invite.setGuestEmails(userDao.findGuestEmails(invite.getInviteId()));
            invite.setRestaurants(restaurantDao.findByInviteId(invite.getInviteId()));
        });

        return invites;
    }

    public Invite getInvite(Long inviteId) {
        Invite invite = inviteDao.findById(inviteId);

        List<Email> guestEmails = userDao.findGuestEmails(invite.getInviteId());
        invite.setGuestEmails(guestEmails);

        List<Restaurant> restaurants = restaurantDao.findByInviteId(invite.getInviteId());
        invite.setRestaurants(restaurants);

        return invite;
    }

    public List<Invite> getInvitationsTo(Long emailId) {
        List<Invite> invites = inviteDao.findByEmailId(emailId);
        invites.forEach(invite -> {
            invite.setGuestEmails(userDao.findGuestEmails(invite.getInviteId()));
            invite.setRestaurants(restaurantDao.findByInviteId(invite.getInviteId()));
        });
        return invites;
    }

    public List<Guest> getInvitationLinks(String baseUrl, Long inviteId) {
        List<String> links = new ArrayList<>();
        List<Guest> guests = inviteDao.findGuests(inviteId);
        guests.forEach(guest -> {
            String inviteCode = encode(guest.getEmail().getEmailId(), guest.getInviteId());
            guest.setInvitationLink(baseUrl + inviteCode);
        });

        return guests;
    }

    public Invite getInvitation(String inviteCode) {
        Long inviteId = decodeInviteId(inviteCode);
        Integer guestEmailId = decodeEmailId(inviteCode);

        Invite invite = this.getInvite(inviteId);

        // Get only the email of the invited guest
        List<Email> guestEmail = invite.getGuestEmails().stream()
                .filter(email -> email.getEmailId().equals(guestEmailId))
                .collect(Collectors.toList());
        invite.setGuestEmails(guestEmail);

        return invite;
    }

    public boolean approveRestaurant(ApproveDTO approve) {
        return restaurantDao.approve(approve.getRestaurantId(), approve.getInviteId(), approve.getEmailId(),
                approve.isApprove());
    }

    public List<RestaurantApproval> getRestaurantApprovals(Long inviteId, Integer emailId) {
        return restaurantDao.findApprovals(inviteId, emailId);
    }

    public List<RestaurantApprovalCount> getRestaurantApprovalCounts(Long inviteId) {
        return restaurantDao.findByInviteId(inviteId).stream().map(restaurant -> {
            RestaurantApprovalCount approvalCount = new RestaurantApprovalCount();
            approvalCount.setInviteId(inviteId);
            approvalCount.setRestaurantId(restaurant.getRestaurantId());

            approvalCount.setThumbsUp(restaurantDao
                    .getApprovalCount(RestaurantDao.THUMBS_UP, inviteId, restaurant.getRestaurantId()));

            approvalCount.setThumbsDown(restaurantDao
                    .getApprovalCount(RestaurantDao.THUMBS_DOWN, inviteId, restaurant.getRestaurantId()));

            return approvalCount;
        }).collect(Collectors.toList());
    }

    private static final int ENCODE_EMAIL_INDEX = 0;
    private static final int ENCODE_INVITE_INDEX = 1;

    private String encode(Integer emailId, Long inviteId) {
        String encode = String.join(",", emailId.toString(), inviteId.toString());
        return Base62.encode(encode);
    }

    private Integer decodeEmailId(String encoded) {
        String decoded = Base62.decode(encoded);
        String emailId = decoded.split(",")[ENCODE_EMAIL_INDEX];
        return Integer.getInteger(emailId);
    }

    private Long decodeInviteId(String encoded) {
        String decoded = Base62.decode(encoded);
        String inviteId = decoded.split(",")[ENCODE_INVITE_INDEX];
        return Long.getLong(inviteId);
    }

}

