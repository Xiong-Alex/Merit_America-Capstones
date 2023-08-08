package com.techelevator.model;

public class ApproveDTO {
    private Long restaurantId;
    private Long inviteId;
    private Integer emailId;

    private boolean approve;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public Integer getEmailId() {
        return emailId;
    }

    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
    }

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    @Override
    public String toString() {
        return "ApproveDTO{" +
                "restaurantId=" + restaurantId +
                ", inviteId=" + inviteId +
                ", emailId=" + emailId +
                ", approve=" + approve +
                '}';
    }
}
