package com.example.shapelyapp.model;

import java.util.ArrayList;

public class Event {
    private String description;
    private String date;
    private String time;

    private String id;
    //private boolean isnotify;
    private boolean isDone;


    public Event() {
    }

    public Event(String description, String date, String time,String id) {
        this.description = description;
        this.date = date;
        this.time = time;
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public Event setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Event setDate(String date) {
        this.date = date;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Event setTime(String time) {
        this.time = time;
        return this;
    }

    public String getId() {
        return id;
    }

    public Event setId(String id) {
        this.id = id;
        return this;
    }

//    public boolean isIsnotify() {
//        return isnotify;
//    }
//
//    public Event setIsnotify(boolean isnotify) {
//        this.isnotify = isnotify;
//        return this;
//    }

    public boolean getIsDone() {
        return isDone;
    }

    public Event setIsDone(boolean done) {
        isDone = done;
        return this;
    }
}
