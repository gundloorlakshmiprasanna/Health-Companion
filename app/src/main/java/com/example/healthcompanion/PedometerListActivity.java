package com.example.healthcompanion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PedometerListActivity extends Activity {

    private ListView mSensorListView;
    private ListAdapter mListAdapter;
    private StepsDBHelper mStepsDBHelper;
    private ArrayList<com.example.healthcompanion.DateStepsModel> mStepCountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer_list);
        mSensorListView = (ListView) findViewById(R.id.steps_list);

        getDataForList();

        mListAdapter = new ListAdapter();
        mSensorListView.setAdapter(mListAdapter);

        Intent mStepsIntent = new Intent(getApplicationContext(), StepsService.class);
        startService(mStepsIntent);

    }

    private void getDataForList() {
        mStepsDBHelper = new StepsDBHelper(this);
        mStepCountList = mStepsDBHelper.readStepsEntries();
    }

    public class DateStepsModel {


        public String mDate;
        public int mStepCount;
    }

    class ListAdapter extends BaseAdapter {

        TextView mDateStepCountText;
        ArrayList<com.example.healthcompanion.DateStepsModel> mStepCountList;
        Context mContext;
        LayoutInflater mLayoutInflater;

        public  ListAdapter(ArrayList<com.example.healthcompanion.DateStepsModel> mStepCountList, Context mContext){
            this.mStepCountList = mStepCountList;
            this.mContext = mContext;
            this.mLayoutInflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public ListAdapter() {

        }


        @Override
        public int getCount() {

            return mStepCountList.size();
        }

        @Override
        public Object getItem(int position) {

            return mStepCountList.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {

                convertView = mLayoutInflater.inflate(R.layout.list_rows, parent, false);
            }

            mDateStepCountText = (TextView) convertView.findViewById(R.id.sensor_name);
            mDateStepCountText.setText(mStepCountList.get(position).mDate + " - Total Steps: " + String.valueOf(mStepCountList.get(position).mStepCount));

            return convertView;
        }
    }

}