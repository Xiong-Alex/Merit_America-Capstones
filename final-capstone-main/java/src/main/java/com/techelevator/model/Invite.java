package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public class Invite {
    private Long inviteId;
    private User createdBy;
    private LocalDate decisionBy;
    private String message;
    private List<Email> guestEmails;
    private List<Restaurant> restaurants;

    public Invite() {
    }

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long id) {
        this.inviteId = id;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getDecisionBy() {
        return decisionBy;
    }

    public void setDecisionBy(LocalDate decisionBy) {
        this.decisionBy = decisionBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Email> getGuestEmails() {
        return guestEmails;
    }

    public void setGuestEmails(List<Email> guestEmails) {
        this.guestEmails = guestEmails;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "Invite{" +
                "inviteId=" + inviteId +
                ", createdBy=" + createdBy +
                ", decisionBy=" + decisionBy +
                ", message='" + message + '\'' +
                ", guestEmails=" + guestEmails +
                ", restaurants=" + restaurants +
                '}';
    }
}
