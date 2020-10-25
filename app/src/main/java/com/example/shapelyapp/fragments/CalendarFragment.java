package com.example.shapelyapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shapelyapp.callbacks.CallBackActivity;
import com.example.shapelyapp.storage.MyList;
import com.example.shapelyapp.storage.MyLists;
import com.example.shapelyapp.R;
import com.example.shapelyapp.model.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarFragment extends Fragment {

    private CallBackActivity callBack_activityList;

    private View view;
    private CalendarView calendar_CV_calendar;
    private RecyclerView recyclerView;
    private Context context;


    public CalendarFragment(){

    }

    public CalendarFragment(Context context) {
        this.context = context;
    }


    public void setCallBack(CallBackActivity _callBack_activityList) {
        this.callBack_activityList = _callBack_activityList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
        Log.d("pttt", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView");
        if(view==null){
            view = inflater.inflate(R.layout.fragment_calendar, container, false);
        }

        findViews(view);

        calendar_CV_calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");

                String myMonth = "";
                String myday = "";
                month = month + 1;
                if((month) < 10){
                    myMonth = "0"+(month);
                }else{
                    myMonth = ""+(month);
                }
                if(dayOfMonth < 10){
                    myday = "0"+dayOfMonth;
                }else{
                    myday = ""+dayOfMonth;
                }
                String date = "" + year + "/" + myMonth + "/" +myday;
                new MyList(recyclerView, context, MyLists.makeTimeTasksList(date));
            }
        });

        return view;
    }



    private void findViews(View view) {
        calendar_CV_calendar = view.findViewById(R.id.calendar_CV_calendar);
        recyclerView = view.findViewById(R.id.calendar_RV_tasks);
    }

    private void setEventOnCalendar(){
        for(Event task : MyLists.taskList){
            String date = task.getDate();
            String parts[] = date.split("/");

            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            long milliTime = calendar.getTimeInMillis();
            calendar_CV_calendar.setDate (milliTime, true, true);
        }

    }






}