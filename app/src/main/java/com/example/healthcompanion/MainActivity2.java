package com.example.healthcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    private FirebaseAuth mFirebaseAuth;
    private Button logOut;
    private RecyclerViewAdapter.RecyclerViewClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Activities");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        logOut = (Button) findViewById(R.id.btnLogOut);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                signOutUser();
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();


        recyclerViewAdapter = new RecyclerViewAdapter(arr(), getApplicationContext());
        recyclerView.setAdapter(recyclerViewAdapter);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }


    private void signOutUser() {

        Intent mainActivity2 = new Intent(MainActivity2.this, MainActivity.class);
        mainActivity2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK / Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivity2);
        finish();
    }

    public ArrayList<Model> arr() {
        ArrayList<Model> holder = new ArrayList<>();

        Model obj1 = new Model();
        obj1.setText("SLEEP");
        obj1.setImage(R.drawable.sleep);
        holder.add(obj1);


        Model obj2 = new Model();
        obj2.setText("EXERCISE");
        obj2.setImage(R.drawable.exercise);
        holder.add(obj2);


        Model obj3 = new Model();
        obj3.setText("WATER INTAKE");
        obj3.setImage(R.drawable.water);
        holder.add(obj3);


        Model obj4 = new Model();
        obj4.setText("STEP COUNT");
        obj4.setImage(R.drawable.stepcount);
        holder.add(obj4);
        return holder;
    }
}
