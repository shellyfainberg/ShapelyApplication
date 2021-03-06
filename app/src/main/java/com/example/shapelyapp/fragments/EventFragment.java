package com.example.shapelyapp.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.shapelyapp.callbacks.CallBackActivity;
import com.example.shapelyapp.R;
import com.example.shapelyapp.model.Event;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

public class EventFragment extends Fragment {

    private CallBackActivity callBack_activityList;

    private View view;
    private EditText task_EDT_task, task_EDT_date, task_EDT_time;
    private Context context;
    private String android_id;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    int PLACE_PICKER_REQUEST = 1;
    private Button btn_check;
    private String dateForDatabase = "";

    public EventFragment(){
    }

    public EventFragment(Context context, String android_id, Toolbar toolbar) {
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
            view = inflater.inflate(R.layout.event_fragment, container, false);
        }

        findViews(view);

        task_EDT_task.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId){
                    case EditorInfo.IME_ACTION_DONE:
                        break;
                }
                return false;
            }
        });

        myRef = database.getReference("message");

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show();
                String description = task_EDT_task.getText().toString();
                String date = task_EDT_date.getText().toString();
                String time = task_EDT_time.getText().toString();
                String id = String.valueOf(System.currentTimeMillis());

                Event task = new Event(description, dateForDatabase, time,id);
                myRef.child("Users").child(android_id).child(""+ id).setValue(task);

                task_EDT_task.setText("");
                task_EDT_date.setText("");
                task_EDT_time.setText("");
                callBack_activityList.setCheckToolbar();
            }
        });

        task_EDT_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        task_EDT_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });
        return view;
    }

    private void showTimeDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                context,
                timeSetListener,
                Calendar.getInstance().get(Calendar.HOUR),
                Calendar.getInstance().get(Calendar.MINUTE),
                DateFormat.is24HourFormat(context)
        );
        timePickerDialog.show();
    }

    private void showDateDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                dateSetListener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String houre = "";
            String myMinute = "";
            if(hourOfDay < 10){
                houre = "0"+hourOfDay;
            }else{
                houre = ""+hourOfDay;
            }
            if(minute < 10){
                myMinute = "0"+minute;
            }else{
                myMinute = ""+minute;
            }
            String time = "" + houre + ":" + myMinute;
            task_EDT_time.setText(time);
        }
    };

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String myMonth = "";
            String myday = "";
            if((month+1) < 10){
                myMonth = "0"+(month+1);
            }else{
                myMonth = ""+(month+1);
            }
            if(dayOfMonth < 10){
                myday = "0"+dayOfMonth;
            }else{
                myday = ""+dayOfMonth;
            }
            String date = "" + myMonth + "/" + myday + "/" + year;
            dateForDatabase = "" + year + "/" + myMonth + "/" + myday;
            task_EDT_date.setText(date);
        }
    };

    private void findViews(View view) {
        task_EDT_task = view.findViewById(R.id.task_EDT_task);
        task_EDT_date = view.findViewById(R.id.task_EDT_date);
        task_EDT_time = view.findViewById(R.id.task_EDT_time);
        btn_check = view.findViewById(R.id.task_BTN_check);
    }
}