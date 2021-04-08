package com.example.healthcompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterWater extends RecyclerView.Adapter<RecyclerViewAdapterWater.WaterViewHolder> {

    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<WaterModel> mGlassCount;

    public RecyclerViewAdapterWater(Context mContext, ArrayList<WaterModel> mGlassCount){
        super();
        this.mContext=mContext;
        this.mGlassCount=mGlassCount;
        this.mLayoutInflater=(LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public RecyclerViewAdapterWater.WaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.water_intake_view,parent,false);
        WaterViewHolder waterVH = new WaterViewHolder(view);
        return new WaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaterViewHolder holder, int position) {
        WaterModel model = mGlassCount.get(position);
        holder.mGlassCount.setText(String.valueOf(mGlassCount.get(position)));
        holder.date.setText(mGlassCount.get(position).date);

    }

    @Override
    public int getItemCount() {
        return mGlassCount.size();

    }


    public class WaterViewHolder extends RecyclerView.ViewHolder{

        TextView date;
        TextView mGlassCount;

        public WaterViewHolder(@NonNull View itemView) {
            super(itemView);
            date=(TextView)itemView.findViewById(R.id.date);
            mGlassCount = (TextView) itemView.findViewById(R.id.glassCount);
        }
    }
}

