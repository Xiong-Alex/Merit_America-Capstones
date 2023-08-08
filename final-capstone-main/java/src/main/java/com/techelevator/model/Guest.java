package com.techelevator.model;

public class Guest {
    private Email email;
    private Long inviteId;
    private String invitationLink;

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public String getInvitationLink() {
        return invitationLink;
    }

    public void setInvitationLink(String invitationLink) {
        this.invitationLink = invitationLink;
    }
}
