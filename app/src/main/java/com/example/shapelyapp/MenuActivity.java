package com.example.shapelyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shapelyapp.model.User;
import com.example.shapelyapp.storage.MySp;

public class MenuActivity extends AppCompatActivity {

    private ImageButton menu_IMBTN_calender;
    private ImageButton menu_IMBTN_workout;
    private ImageButton menu_IMBTN_foodmood;
    private ImageButton menu_IMBTN_bpm;
    private Button menu_BTN_back;
    private Button menu_BTN_logOut;
    private MySp mySp;
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mySp = new MySp(this);
        currUser = (User)getIntent().getSerializableExtra(getString(R.string.user_id));
        findView();

        menu_IMBTN_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, CalendarActivity.class);
                startActivity(intent);
                finish();
            }
        });


        menu_IMBTN_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, WorkoutActivity.class);
                startActivity(intent);
                finish();
            }
        });


        menu_IMBTN_foodmood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, FoodMoodActivity.class);
                intent.putExtra(getString(R.string.user_id), currUser);
                startActivity(intent);
                finish();
            }
        });


        menu_IMBTN_bpm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, StepCounterActivity.class);
                startActivity(intent);
                finish();
            }
        });


        menu_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        menu_BTN_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });


    }

    private void logOut() {
        mySp.deleteString(MySp.KEYS.CURRENT_USER);
        openWelcome();
        finish();


    }

    private void openWelcome() {
        Intent intent = new Intent(MenuActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    private void findView() {

        menu_IMBTN_calender = findViewById(R.id.menu_IMBTN_calender);
        menu_IMBTN_workout = findViewById(R.id.menu_IMBTN_workout);
        menu_IMBTN_foodmood = findViewById(R.id.menu_IMBTN_foodmood);
        menu_IMBTN_bpm = findViewById(R.id.menu_IMBTN_bpm);
        menu_BTN_back = findViewById(R.id.menu_BTN_back);
        menu_BTN_logOut = findViewById(R.id.menu_BTN_logOut);



    }
}