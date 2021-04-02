package com.example.healthcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import static com.example.healthcompanion.DatabaseHelper.CREATION_DATE;
import static com.example.healthcompanion.DatabaseHelper.START;
import static com.example.healthcompanion.DatabaseHelper.STOP;


public class Exercise extends AppCompatActivity {

    private ExerciseDBManager exerciseDbManager;
    private SimpleCursorAdapter adapter;


    private RecyclerView mExerciseRecyclerView;
    private RecyclerViewAdapterExercise mRecyclerViewAdapterExercise;
    private DatabaseHelper mdbHelper;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ExerciseModel> mExerciseList;



    TextView id,creationDate,START,STOP;

    Button btn1,btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        creationDate =  findViewById(R.id.date);
        START =  findViewById(R.id.startTime);
        STOP=  findViewById(R.id.stopTime);




        exerciseDbManager = new ExerciseDBManager(this);
        exerciseDbManager.open();


        Cursor cursor = exerciseDbManager.readExerciseData();

        mExerciseRecyclerView=findViewById(R.id.exerciserv);
        mRecyclerViewAdapterExercise = new RecyclerViewAdapterExercise(Exercise.this, mExerciseList);
        mExerciseRecyclerView.setAdapter(mRecyclerViewAdapterExercise);


        mExerciseRecyclerView.setLayoutManager(new LinearLayoutManager(Exercise.this));



        btn1=(Button) findViewById(R.id.startBtn);
        btn2=(Button) findViewById(R.id.stopBtn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.VISIBLE);

                DatabaseHelper dbHelper = new DatabaseHelper(Exercise.this);
                dbHelper.addTime(creationDate.getText().toString().trim(),START.getText().toString().trim(),null );


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2.setVisibility(View.GONE);
                btn1.setVisibility(View.VISIBLE);

                DatabaseHelper dbHelper = new DatabaseHelper(Exercise.this);
                dbHelper.addTime(null,null,STOP.getText().toString().trim());

            }
        });
    }

    private void getDataForList() {

        mdbHelper = new DatabaseHelper(this);
        mExerciseList = mdbHelper.readExerciseData();

    }

}