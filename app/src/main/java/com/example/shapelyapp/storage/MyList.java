package com.example.shapelyapp.storage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shapelyapp.R;
import com.example.shapelyapp.callbacks.CallBackActivity;
import com.example.shapelyapp.callbacks.CallBackUsersReady;
import com.example.shapelyapp.fragments.EventListFragment;
import com.example.shapelyapp.model.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyList {

    private AdapterTaskModel adapter_taskModel;
    private RecyclerView recyclerView;
    private Context context;
    private ArrayList<Event> tasks;
    private int mposition;
    private String fragmentName ="";
    private CallBackActivity callBack_activityList;

    public MyList() {
    }

    public MyList(RecyclerView recyclerView, Context context, ArrayList<Event> tasks){
        this.recyclerView = recyclerView;
        this.context = context;
        refreshList(tasks);
    }

    public MyList(RecyclerView recyclerView, Context context, EventListFragment toDoListFragment) {
        this.recyclerView = recyclerView;
        this.context = context;
        fragmentName = toDoListFragment.getClass().getName();
        Log.d("ooo", "name: " + fragmentName);
        initList();

    }

    private void initList() {
        tasks = getNotes();

    }

    private void openNote(Event task) {
        Toast.makeText(context, task.getDate(), Toast.LENGTH_SHORT).show();
    }

    private ArrayList<Event> getNotes() {
        tasks = new ArrayList<>();
        Log.d("pttt", "C - Number of tasks111: " + tasks.size());
        MyFirebase.getUsers(new CallBackUsersReady() {

            @Override
            public void TasksReady(ArrayList<Event> tasks) {
                refreshList(tasks);
                Log.d("pttt", "C - Number of tasks: " + tasks.size());
            }
            @Override
            public void error() { }
        }, context);

        Log.d("pttt", "C - Number of tasks: " + tasks.size());
        return tasks;
    }
    private void refreshList(ArrayList<Event> tasks) {
        this.tasks = tasks;
        adapter_taskModel = new AdapterTaskModel(context, tasks);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_taskModel);
    }

    public  void removall(){
        recyclerView.removeAllViews();
    }
}