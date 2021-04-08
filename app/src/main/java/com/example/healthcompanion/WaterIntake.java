package com.example.healthcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class WaterIntake extends AppCompatActivity {

    RecyclerView mWaterRecyclerView;
    RecyclerViewAdapterWater mRecyclerViewAdapterWater;
    DatabaseHelper dbHelper;
    LinearLayoutManager linearLayoutManager;
    ArrayList<WaterModel> mGlassCountList;
    Intent intent;



    FloatingActionButton addSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_intake);


        mWaterRecyclerView =(RecyclerView) findViewById(R.id.waterRv);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mWaterRecyclerView.setLayoutManager(linearLayoutManager);

        getDataForList();

        mRecyclerViewAdapterWater = new RecyclerViewAdapterWater(WaterIntake.this,mGlassCountList);
        mWaterRecyclerView.setAdapter(mRecyclerViewAdapterWater);

        addSchedule=(FloatingActionButton) findViewById(R.id.addSchedule);
        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(WaterIntake.this,AddSchedule.class);
                startActivity(intent);
            }
        });
    }

    private void getDataForList() {
        dbHelper = new DatabaseHelper(this);
        mGlassCountList = dbHelper.readGlassCount();
    }
}

