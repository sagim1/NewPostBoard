package com.myproject.postboard;

/**
 * Created by root on 3/27/16.
 */
public class Event {

    private String eventName;
    private String eventDay;
    private String eventTime;
    private String eventPlace;
    private String eventBy;
    private String eventImage;

    public Event()
    {

    }

    public Event(String eventName, String eventDay, String eventTime, String eventPlace, String eventBy, String eventImage) {
        this.eventName = eventName;
        this.eventDay = eventDay;
        this.eventTime = eventTime;
        this.eventPlace = eventPlace;
        this.eventBy = eventBy;
        this.eventImage = eventImage;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDay() {
        return eventDay;
    }

    public void setEventDay(String eventDay) {
        this.eventDay = eventDay;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getEventBy() {
        return eventBy;
    }

    public void setEventBy(String eventBy) {
        this.eventBy = eventBy;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }
}
