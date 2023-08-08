package com.techelevator.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

public class InviteDTO {
    @NotEmpty(message = "Please provied at least one guest.")

    private List<String> guestEmails;
    @NotEmpty(message = "Please provide at least one email.")
    private List<Long> restaurantIds;
//    @NotEmpty(message = "Please provide a datetime.")
    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private LocalDate decisionBy;
    String message;

    public InviteDTO() {
    }

    public List<String> getGuestEmails() {
        return guestEmails;
    }

    public void setGuestEmails(List<String> guestEmails) {
        this.guestEmails = guestEmails;
    }

    public List<Long> getRestaurantIds() {
        return restaurantIds;
    }

    public void setRestaurantIds(List<Long> restaurantIds) {
        this.restaurantIds = restaurantIds;
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

    @Override
    public String toString() {
        return "InviteDTO{" +
                "guestEmails=" + guestEmails +
                ", restaurantIds=" + restaurantIds +
                ", decisionBy=" + decisionBy +
                ", message='" + message + '\'' +
                '}';
    }
}
