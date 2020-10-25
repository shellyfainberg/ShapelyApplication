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
            public void error() {

            }

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
        adapter_taskModel.SetOnItemClickListener(new AdapterTaskModel.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Event task) {
                String l1 = "";
                String l2 = "";
                if(task.getLocation().get(0) !=0 && task.getLocation().get(1) != 0){
                    l1 = "" + task.getLocation().get(0);
                    l2 = "" + task.getLocation().get(1);
                }
                String date = task.getDate();
                if(!date.isEmpty()) {
                    String[] dateArr = date.split("/");
                    String year = "", month = "", day = "";
                    year = dateArr[0];
                    month = dateArr[1];
                    day = dateArr[2];
                    date = month + "/" + day + "/" + year;
                }
                new AlertDialog.Builder(context)
                        .setTitle("Task")
                        .setMessage("Date: " + date + "\nTime: " + task.getTime() + "\nTask: " + task.getDescription()
                                + "\nLatitude: " + l1 + "\nLongitude:" + l2)

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setIcon(R.drawable.ic_ex1)
                        .show();

            }

            @Override
            public void onItemLongClick(View view, int position, Event task) {

                mposition = position;
                if(fragmentName.equals("com.example.todolist.ToDoListFragment"))
                    changeToDone();

            }
        });

    }

    private void changeToDone(){
        Toast.makeText(context, "done", Toast.LENGTH_SHORT).show();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef;
        String id  = tasks.get(mposition).getId();
        String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        myRef = database.getReference("message");
        myRef.child("Users").child(android_id).child(id).child("isDone").setValue(true);
        adapter_taskModel.removeAt(mposition);
        EventListFragment.updateList();
    }

    public  void removall(){
        recyclerView.removeAllViews();
    }

}
