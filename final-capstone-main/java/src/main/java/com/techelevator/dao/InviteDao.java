package com.techelevator.dao;

import com.techelevator.model.*;

import java.time.LocalDateTime;
import java.util.List;

public interface InviteDao {
    Invite findById(Long inviteId);

    Invite save(Invite invite);

    boolean saveToInvitedRestaurant(Long restaurantId, Long inviteId);

    boolean saveInviteGuest(Long inviteId, Integer emailId);

    List<Invite> findByCreatedBy(Long createdById);

    List<Invite> findByEmailId(Long emailId);

    List<Guest> findGuests(Long inviteId);
}
