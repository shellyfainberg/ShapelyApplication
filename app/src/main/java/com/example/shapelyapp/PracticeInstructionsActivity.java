package com.example.shapelyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PracticeInstructionsActivity extends AppCompatActivity {
    private Button preAlone_BTM_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_instructions);
        findView();
        preAlone_BTM_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PracticeInstructionsActivity.this, AlonePracticeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void findView() {

        preAlone_BTM_start = findViewById(R.id.preAlone_BTM_start);
    }
}
