package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.ApproveDTO;
import com.techelevator.model.Restaurant;
import com.techelevator.model.RestaurantApproval;

public interface RestaurantDao {
    boolean THUMBS_UP = true;
    boolean THUMBS_DOWN = false;

    List<Restaurant> findAll();

    List<Restaurant> findBy(String city, String zip, String type);

    Restaurant findById(Long id);

    List<Restaurant> findByInviteId(Long inviteId);

    boolean approve(Long restaurantId, Long inviteId, Integer guestEmailId, boolean isApprove);

    List<RestaurantApproval> findApprovals(Long inviteId, Integer emailId);

    int getApprovalCount(boolean approval, Long inviteId, Long restaurantId);

}
