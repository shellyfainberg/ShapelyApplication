package com.example.shapelyapp.storage;

import com.example.shapelyapp.model.Event;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MyLists {

    private DataSnapshot dataSnapshot;
    public static ArrayList<Event> taskList;
    public static ArrayList<Event> tasksByDateList;
    public static ArrayList<Event> doneTasksList;

    public MyLists() {
    }

    public MyLists(DataSnapshot dataSnapshot) {
        this.dataSnapshot = dataSnapshot;
        taskList = makeTaskList();
        doneTasksList = makeDoneList();
    }

    private   ArrayList<Event> makeDoneList(){
        doneTasksList = new ArrayList<>();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Event task = ds.getValue(Event.class);
            if(task.getIsDone())
                doneTasksList.add(task);
        }
        sortDoneList();
        return doneTasksList;
    }

    private   ArrayList<Event> makeTaskList(){
        taskList = new ArrayList<>();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Event task = ds.getValue(Event.class);
            if(!task.getIsDone())
                taskList.add(task);
        }
        sortList();
        return taskList;
    }

    private void sortList(){
        Collections.sort(taskList, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                String x1 = o1.getDate();
                String x2 = o2.getDate();
                int sComp = x1.compareTo(x2);
                if(sComp != 0){
                    return sComp;
                }
                String y1 = o1.getTime();
                String y2 = o2.getTime();
                return y1.compareTo(y2);
            }
        });
    }
    private void sortDoneList(){
        Collections.sort(doneTasksList, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                String x1 = o1.getDate();
                String x2 = o2.getDate();
                int sComp = x1.compareTo(x2);
                if(sComp != 0){
                    return sComp;
                }
                String y1 = o1.getTime();
                String y2 = o2.getTime();
                return y1.compareTo(y2);
            }
        });
    }


    public static ArrayList<Event> makeTimeTasksList(String date){
        tasksByDateList = new ArrayList<>();
        for(Event t : taskList){
            if(t.getDate().equals(date)){
                tasksByDateList.add(t);
            }

        }
        return tasksByDateList;
    }
    public ArrayList<Event> getTaskList(){
        return taskList;
    }

}
