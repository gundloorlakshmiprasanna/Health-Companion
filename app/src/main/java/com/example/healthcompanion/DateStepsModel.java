package com.example.healthcompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DateStepsModel {


    public String mDate;
    public int mStepCount;
    public int Id;

    public DateStepsModel(){
        this.mDate=mDate;
        this.mStepCount=mStepCount;
        this.Id=Id;
    }

    public int getId(){
        return Id;
    }

    public void setId(int Id){
        this.Id=Id;
    }

    public String getmDate(){
        return mDate;
    }

    public void setmDate(String mDate){
        this.mDate=mDate;
    }
    public int getmStepCount(){
        return mStepCount;
    }
    public void setmStepCount(int mStepCount){
        this.mStepCount=mStepCount;
    }




}


