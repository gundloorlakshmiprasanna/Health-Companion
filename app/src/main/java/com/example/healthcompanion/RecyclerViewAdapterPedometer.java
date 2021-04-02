package com.example.healthcompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapterPedometer extends RecyclerView.Adapter<RecyclerViewAdapterPedometer.PedometerViewHolder> {

    TextView mDateStepCountText , id;
    ArrayList<DateStepsModel> mStepCountList;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public  RecyclerViewAdapterPedometer(Context mContext, ArrayList<DateStepsModel> mStepCountList){

        super();
        this.mStepCountList = mStepCountList;
        this.mContext = mContext;
        this.id= id;
        this.mLayoutInflater = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerViewAdapterPedometer.PedometerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from((parent.getContext()));
        View view = inflater.inflate(R.layout.stepcount_layout, parent, false);
        PedometerViewHolder vh = new PedometerViewHolder(view);
        return new PedometerViewHolder(view) ;

    }


    @Override
    public void onBindViewHolder (@NonNull PedometerViewHolder holder, int position){
        DateStepsModel model=mStepCountList.get(position);
        holder.Id.setText(String.valueOf(mStepCountList.get(position).getId()));
        holder.mDateStepCountText.setText(String.valueOf(mStepCountList.get(position).mStepCount));
        holder.mDate.setText(mStepCountList.get(position).mDate);
    }

    @Override
    public int getItemCount() {

        return mStepCountList.size();
    }


    public class PedometerViewHolder extends RecyclerView.ViewHolder {
        TextView mDate , Id;
        TextView  mDateStepCountText ;
        public PedometerViewHolder(@NonNull View itemView) {
            super(itemView);
            Id=(TextView) itemView.findViewById(R.id.id);
            mDate = (TextView) itemView.findViewById(R.id.date);
            mDateStepCountText=(TextView)itemView.findViewById(R.id.sensor_name);
        }
    }
}





