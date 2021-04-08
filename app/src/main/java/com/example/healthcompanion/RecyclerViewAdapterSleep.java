package com.example.healthcompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterSleep extends RecyclerView.Adapter<RecyclerViewAdapterSleep.SleepViewHolder> {

    TextView date , analysis ,id;
    ArrayList<SleepModel> mSleepDuration;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public  RecyclerViewAdapterSleep(Context mContext, ArrayList<SleepModel> mSleepDuration){

        super();
        this.mSleepDuration = mSleepDuration;
        this.mContext = mContext;
        this.id= id;
        this.mLayoutInflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerViewAdapterSleep.SleepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from((parent.getContext()));
        View view = inflater.inflate(R.layout.sleep_view, parent, false);
        SleepViewHolder vh = new SleepViewHolder(view);
        return new SleepViewHolder(view) ;

    }


    @Override
    public void onBindViewHolder (@NonNull SleepViewHolder holder, int position){
        SleepModel model=mSleepDuration.get(position);
        holder.id.setText(model.getid());
        holder.analysis.setText(model.getanalysis());
        holder.date.setText(mSleepDuration.get(position).date);
    }

    @Override
    public int getItemCount() {

        return mSleepDuration.size();
    }


    public class SleepViewHolder extends RecyclerView.ViewHolder {
        TextView date , id;
        TextView  analysis ;
        public SleepViewHolder(@NonNull View itemView) {
            super(itemView);
            id=(TextView) itemView.findViewById(R.id.id);
            date = (TextView) itemView.findViewById(R.id.date);
            analysis=(TextView)itemView.findViewById(R.id.comment);
        }
    }
}
