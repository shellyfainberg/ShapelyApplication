package com.example.shapelyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class AboutusActivity extends AppCompatActivity {

    private Button aboutUs_BTN_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        findView();

        aboutUs_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutusActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void findView() {

        aboutUs_BTN_back= findViewById(R.id.aboutUs_BTN_back);
    }
}