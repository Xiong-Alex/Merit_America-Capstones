package com.techelevator.model;

import java.time.LocalTime;

public class HoursOfOperation {
    private String dayOpen;
    private LocalTime openFrom;
    private LocalTime openTo;
    
    public HoursOfOperation(String dayOpen, LocalTime openFrom, LocalTime openTo) {
        this.dayOpen = dayOpen;
        this.openFrom = openFrom;
        this.openTo = openTo;
    }

    public String getDayOpen() {
        return dayOpen;
    }

    public void setDayOpen(String dayOpen) {
        this.dayOpen = dayOpen;
    }

    public LocalTime getOpenFrom() {
        return openFrom;
    }

    public void setOpenFrom(LocalTime openFrom) {
        this.openFrom = openFrom;
    }

    public LocalTime getOpenTo() {
        return openTo;
    }

    public void setOpenTo(LocalTime openTo) {
        this.openTo = openTo;
    }

    @Override
    public String toString() {
        return "HoursOfOperation [dayOpen=" + dayOpen + ", openFrom=" + openFrom + ", openTo=" + openTo + "]";
    }
}
