package com.example.healthcompanion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PedometerListActivity extends Activity {

    private RecyclerView mSensorRecyclerView;
    private RecyclerViewAdapterPedometer mRecyclerViewAdapterPedometer;
    private DatabaseHelper mDatabaseHelper;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<DateStepsModel> mStepCountList;
            Intent mStepsIntent;

            final  String [] from = new String[]{ DatabaseHelper.ID,DatabaseHelper.CREATION_DATE,DatabaseHelper.STEPS_COUNT };

            final int[] to = new int[] {R.id.id,R.id.date,R.id.sensor_name};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Step Count");
        setContentView(R.layout.activity_pedometer_list);

        mSensorRecyclerView = (RecyclerView) findViewById(R.id.steps_list);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mSensorRecyclerView.setLayoutManager(linearLayoutManager);

        getDataForList();

        mRecyclerViewAdapterPedometer = new RecyclerViewAdapterPedometer(PedometerListActivity.this, mStepCountList);
        mSensorRecyclerView.setAdapter(mRecyclerViewAdapterPedometer);

        mStepsIntent = new Intent(getApplicationContext(), StepsService.class);
        startService(mStepsIntent);

    }

    private void getDataForList() {
        mDatabaseHelper = new DatabaseHelper(this);
        mStepCountList = mDatabaseHelper.readStepsEntries();
    }
}