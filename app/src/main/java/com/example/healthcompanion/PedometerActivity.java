package com.example.healthcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PedometerActivity extends AppCompatActivity {

    Button startCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.steps);
        setTitle("Step Counter");

        startCount = (Button) findViewById(R.id.startCount);
        startCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PedometerActivity.this, StepCountActivity.class));
            }
        });

    }
}
