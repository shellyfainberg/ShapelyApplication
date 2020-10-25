package com.example.shapelyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowPracticeActivity extends AppCompatActivity {



    private ListView show_LV_recipesList;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpractice);

        findView();

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Jumping jacks ");
        arrayList.add("Wall sit");
        arrayList.add("Push up");
        arrayList.add("Abdominal crunch");
        arrayList.add(" Step up on to chair");
        arrayList.add("Squat");
        arrayList.add("Triceps dip on chair");
        arrayList.add("High knees running to place");
        arrayList.add("Lunge");
        arrayList.add("Push up and routation");
        arrayList.add("Side Plank ");



        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        show_LV_recipesList.setAdapter(arrayAdapter);



    }

    public void createNewDialog() {

        dialogBuilder = new AlertDialog.Builder(this);

        final View contactPopupView = getLayoutInflater().inflate(R.layout.recipepopup, null);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();



    }

    private void findView() {
        show_LV_recipesList = findViewById(R.id.show_LV_recipesList);


    }
}

