package com.example.healthcompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordPage extends AppCompatActivity {
    private EditText passwordEmail;
    private Button resetPassword;
    private TextView signIn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);
        setTitle("Health Companion");

        passwordEmail = (EditText)findViewById(R.id.enterEmail);
        resetPassword = (Button)findViewById(R.id.resetPassword);
        signIn = (TextView)findViewById(R.id.signIn);
        firebaseAuth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = passwordEmail.getText().toString().trim();

                if(useremail.isEmpty()){
                    Toast.makeText(ForgotPasswordPage.this, "Please enter your registered email ID", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(ForgotPasswordPage.this,"Password reset email sent!", Toast.LENGTH_SHORT).show();
                               finish();
                               startActivity(new Intent(ForgotPasswordPage.this, MainActivity.class));
                           }
                           else {
                               Toast.makeText(ForgotPasswordPage.this,"Error in sending password rest email", Toast.LENGTH_SHORT).show();
                           }
                        }
                    });
                }
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPasswordPage.this, MainActivity.class));
            }
        });

    }
}