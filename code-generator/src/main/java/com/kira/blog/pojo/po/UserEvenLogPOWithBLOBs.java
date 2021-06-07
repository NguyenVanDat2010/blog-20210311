package com.kira.blog.pojo.po;

public class UserEvenLogPOWithBLOBs extends UserEvenLogPO {
    private String eventName;

    private String details;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}