package com.example.shapelyapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeActivity extends AppCompatActivity {

    private Button main_BTN_signIn;
    private Button main_BTN_logIn;
    private Button main_BTN_aboutUs;
    private  Button main_BTN_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findView();
        main_BTN_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SigninActivity.class);
                startActivity(intent);
                finish();

            }
        });
        main_BTN_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        main_BTN_aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,AboutusActivity.class);
                startActivity(intent);
                finish();
            }
        });
        main_BTN_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void findView() {
        main_BTN_signIn = findViewById(R.id.main_BTN_signIn);
        main_BTN_logIn = findViewById(R.id.main_BTN_logIn);
        main_BTN_aboutUs = findViewById(R.id.main_BTN_aboutUs);
        main_BTN_menu = findViewById(R.id.main_BTN_menu);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
}