package com.example.shapelyapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shapelyapp.callbacks.CallBackActivity;
import com.example.shapelyapp.fragments.CalendarFragment;
import com.example.shapelyapp.fragments.EventFragment;
import com.example.shapelyapp.fragments.EventListFragment;
import com.example.shapelyapp.model.Event;
import com.example.shapelyapp.storage.MyLists;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;

    private EventListFragment eventListFragment;
    private EventFragment eventFragment;
    private CalendarFragment calendarFragment;

    public static ArrayList<Event> allTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        allTasks = new ArrayList<>();

        @SuppressLint("HardwareIds") String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);


        eventListFragment = new EventListFragment(this, android_id);
        eventFragment = new EventFragment(this, android_id, toolbar);
        calendarFragment = new CalendarFragment(this);


        eventListFragment.setCallBack(callBack_activity);
        eventFragment.setCallBack(callBack_activity);
        calendarFragment.setCallBack(callBack_activity);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_FLAY_mainScreen, eventListFragment).addToBackStack(null).commit();


        bottomNavigationView = findViewById(R.id.main_BTN_navigation);

        setSupportActionBar(toolbar);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_calendar) {
                    goTo(calendarFragment);
                } else if (id == R.id.action_list) {
                    goTo(eventListFragment);
                } else if (id == R.id.action_back) {
                    startActivity(new Intent(CalendarActivity.this, MenuActivity.class));
                    finish();
                }
                return true;
            }
        });

        final Handler ha = new Handler();
        ha.postDelayed(new Runnable() {

            @Override
            public void run() {
                Log.d("ptttLF", "Ticker");

                allTasks = MyLists.taskList;
                Log.d("jjj", "" + allTasks);

                ha.postDelayed(this, 5000);
            }
        }, 1000);


    }

    private void goTo(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_FLAY_mainScreen, fragment).addToBackStack(null).commit();
    }

    CallBackActivity callBack_activity = new CallBackActivity() {
        @Override
        public void goToTaskPage() {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_FLAY_mainScreen, eventFragment).addToBackStack(null).commit();
        }


        @Override
        public void setCheckToolbar() {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_FLAY_mainScreen, eventListFragment).addToBackStack(null).commit();
        }
    };


    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount() == 0){
            startActivity(new Intent(CalendarActivity.this, MenuActivity.class));
            finish();
        }else{
            super.onBackPressed();
        }
    }
}
