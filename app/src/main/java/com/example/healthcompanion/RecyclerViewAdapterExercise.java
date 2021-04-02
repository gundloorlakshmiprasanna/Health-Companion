package com.example.healthcompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterExercise extends RecyclerView.Adapter<RecyclerViewAdapterExercise.ExerciseViewHolder> {

    private Context context;
    private ArrayList<ExerciseModel> mExerciseList;

    public RecyclerViewAdapterExercise(Context context, ArrayList<ExerciseModel> mExerciseList){
        this.context=context;
        this.mExerciseList=mExerciseList;
    }


    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_view, parent,false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterExercise.ExerciseViewHolder holder, int position) {
        ExerciseModel model = mExerciseList.get(position);
        holder.date.setText(model.getCreationDate());
        holder.startTime.setText(model.getSTART());
        holder.stopTime.setText(model.getSTOP());
    }

    @Override
    public int getItemCount() {
        ArrayList<ExerciseModel> mExerciseList = new ArrayList<ExerciseModel>();
        return mExerciseList.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder{

         TextView date, startTime , stopTime;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            startTime=itemView.findViewById(R.id.startTime);
            stopTime=itemView.findViewById(R.id.stopTime);
        }
    }
}
