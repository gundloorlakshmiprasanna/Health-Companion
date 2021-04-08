package com.example.healthcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Sleep extends AppCompatActivity {

    RecyclerView mSleepRecyclerView;
    RecyclerViewAdapterSleep mRecyclerViewAdapterSleep;
    DatabaseHelper dbHelper;
    LinearLayoutManager linearLayoutManager;
    ArrayList<SleepModel> mSleepDuration;
    Intent intent;

    TextView comment;
    Button startBtn,stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        mSleepRecyclerView = (RecyclerView) findViewById(R.id.sleepRv);
        linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        mSleepRecyclerView.setLayoutManager(linearLayoutManager);

        comment=findViewById(R.id.comment);
        startBtn=findViewById(R.id.startBtn);
        stopBtn=findViewById(R.id.stopBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Sleep.this,SleepTime.class);
                startActivity(intent);
            }
        });

        getDateForList();

        mRecyclerViewAdapterSleep= new RecyclerViewAdapterSleep(Sleep.this,mSleepDuration);
        mSleepRecyclerView.setAdapter(mRecyclerViewAdapterSleep);

        NotificationManager manager=(NotificationManager)getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        manager.cancelAll();

        if(getIntent().hasExtra("Yes")){
            comment.setText("Perfect Sleep");
            comment.setTextColor(Color.GREEN);
        }
        else if (getIntent().hasExtra("No")){
        comment.setText("Over Sleep");
        comment.setTextColor(Color.RED);
        }

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment.setText("Under Sleep");
                comment.setTextColor(Color.RED);
            }
        });


    }

    private void getDateForList() {
        dbHelper = new DatabaseHelper(this);
        mSleepDuration = dbHelper.readDuration();
    }

    public void onStartBtnClick(View view){

        SleepNotificationReceiver.setupAlarm(getApplicationContext());
    }


}