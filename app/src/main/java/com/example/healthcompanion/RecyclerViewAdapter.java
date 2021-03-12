package com.example.healthcompanion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private ArrayList<Model> data;
    private RecyclerViewClickListener listener;

    Context context;
    public RecyclerViewAdapter(ArrayList<Model> data, Context context) {
        this.data = data;
        this.context=context;
    }

    public RecyclerViewAdapter(ArrayList<Model> arr) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageView);
            txt=(TextView)itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();


                 Toast.makeText(context,"position"+position, Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(context, PedometerActivity.class);
                 context.startActivity(intent);






        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final  Model temp = data.get(position);

        holder.txt.setText(data.get(position).getText());
        holder.img.setImageResource(data.get(position).getImage());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(position==3) {
                        Intent intent = new Intent(context, PedometerActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(context, MainActivity2.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }


            }
        });

        
    }

    @Override
    public int getItemCount() {
        return data.size(); }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }


}









