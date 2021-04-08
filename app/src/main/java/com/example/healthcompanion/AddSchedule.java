package com.example.healthcompanion;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class  AddSchedule extends AppCompatActivity implements View.OnClickListener {

    private int notificationId = 1;
    Button setBtn,cancelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);

        setBtn = (Button) findViewById(R.id.set);
        cancelBtn = (Button) findViewById(R.id.cancel);



        setBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

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

        NotificationManager manager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(AddSchedule.this,WaterIntake.class);
        intent1.putExtra("Yes",true);
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP/Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent1 = PendingIntent.getActivity(AddSchedule.this,0,intent1,PendingIntent.FLAG_ONE_SHOT);

        Intent intent2 = new Intent(AddSchedule.this,WaterIntake.class);
        intent1.putExtra("No",false);
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP/Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent2 = PendingIntent.getActivity(AddSchedule.this,1,intent2,PendingIntent.FLAG_ONE_SHOT);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                AddSchedule.this,getString(R.string.app_name)
        );




    }
}
