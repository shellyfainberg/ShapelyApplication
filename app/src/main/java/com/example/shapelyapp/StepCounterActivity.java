package com.example.shapelyapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StepCounterActivity extends AppCompatActivity implements SensorEventListener {
    private Button heartbeat_BTN_CounterReset;
    private TextView heartbeat_TXT_showsteps ;
    private TextView heartbeat_TXT_counter;
    private SensorManager sensorManager;
    private Sensor stepSensor;
    private boolean isCounter;
    int spetCount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        findView();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            stepSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounter=true;
        }
        else{
            heartbeat_TXT_showsteps.setText("Counter is not present");
            isCounter=false;
        }

        heartbeat_BTN_CounterReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spetCount=0;
                heartbeat_TXT_counter.setText(String.valueOf(spetCount));


            }
        });
    }


    private void findView() {
        heartbeat_TXT_showsteps = findViewById(R.id.heartbeat_TXT_showsteps);
        heartbeat_TXT_counter = findViewById(R.id.heartbeat_TXT_counter);
        heartbeat_BTN_CounterReset= findViewById(R.id.heartbeat_BTN_CounterReset);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor == stepSensor){
            spetCount= (int)sensorEvent.values[0];
            heartbeat_TXT_counter.setText(String.valueOf(spetCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {


    }
    @Override
    protected void onResume(){
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            sensorManager.registerListener(this,stepSensor ,SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onPause(){
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            sensorManager.unregisterListener(this,stepSensor );
        }
    }
}
