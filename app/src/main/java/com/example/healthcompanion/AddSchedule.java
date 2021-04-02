package com.example.healthcompanion;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class AddSchedule extends AppCompatActivity implements View.OnClickListener {

    private int notificationId = 1;
    Button setBtn,cancelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);

        setBtn = (Button) findViewById(R.id.set);
        cancelBtn = (Button) findViewById(R.id.cancel);
        FloatingActionButton add = findViewById(R.id.add);


        setBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        TimePicker timePicker=findViewById(R.id.time);

        Intent intent = new Intent(AddSchedule.this,WaterIntakeRemainder.class);
        intent.putExtra("notificationId",notificationId);

        PendingIntent alarmIntent = PendingIntent.getBroadcast(AddSchedule.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (v.getId()){
            case R.id.set:

            case R.id.add:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND , 0);
                long alarmStartTime = startTime.getTimeInMillis();

                alarm.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);

                Toast.makeText(this,"Done!",Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancel:
                alarm.cancel(alarmIntent);

                Toast.makeText(this,"Cancelled ",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
