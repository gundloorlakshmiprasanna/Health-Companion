package com.example.healthcompanion;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StepCountActivity extends AppCompatActivity implements SensorEventListener {


     private SensorManager sensorManager;
     private Sensor sensor;
     private boolean run = false;
     private TextView steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stepcount_layout);
        setTitle("Step Counter");

        steps = (TextView) findViewById(R.id.steps);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
        {
            sensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            run = true;
        }
        else
        {
            run = false;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (run)
        {
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        steps.setText(String.valueOf(sensorEvent.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}









