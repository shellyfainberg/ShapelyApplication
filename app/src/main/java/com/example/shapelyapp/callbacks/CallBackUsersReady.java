package com.example.shapelyapp.callbacks;

import com.example.shapelyapp.model.Event;

import java.util.ArrayList;

public interface CallBackUsersReady {
    void TasksReady(ArrayList<Event> tasks);
    void error();
}
