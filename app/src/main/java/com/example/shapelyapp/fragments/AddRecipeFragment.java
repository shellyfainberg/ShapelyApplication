package com.example.shapelyapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shapelyapp.callbacks.CallBackActivity;
import com.example.shapelyapp.storage.MyList;
import com.example.shapelyapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddRecipeFragment extends Fragment {

    static CallBackActivity callBack_activityList;

    private View view;
    FloatingActionButton floatingActionButton;
    static RecyclerView recyclerView;
    public static Context context;
    private MyList myList;
    private String android_id;


    public AddRecipeFragment(){

    }

    public AddRecipeFragment(Context context, String android_id) {

        this.context = context;
        this.android_id = android_id;

    }


    public void setCallBack(CallBackActivity _callBack_activityList) {
        this.callBack_activityList = _callBack_activityList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("pttt", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView");
        if(view==null){
            view = inflater.inflate(R.layout.event_list_fragment, container, false);
        }

        findViews(view);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo();
            }
        });


        myList = new MyList(recyclerView, context, new EventListFragment());

        return view;
    }

    public static void updateList(){

        new MyList(recyclerView, context, new EventListFragment());
    }


    private void goTo() {
        callBack_activityList.goToTaskPage();

    }

    private void findViews(View view) {
        floatingActionButton = view.findViewById(R.id.list_FABTN_addTask);
        recyclerView = view.findViewById(R.id.list_RV_tasks);


    }

    @Override
    public void onResume() {
        super.onResume();
        myList.removall();
        myList = new MyList(recyclerView, context, new EventListFragment());


    }

}