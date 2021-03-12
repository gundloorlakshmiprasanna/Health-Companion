package com.example.healthcompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPage extends AppCompatActivity {

    private EditText username,emailId,password1,password2;
    private Button btnSignUp;
    private TextView tvSignIn;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        setTitle("Health Companion");

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.enterEmail);
        username = findViewById(R.id.username);
        password1 = findViewById(R.id.enterPassword);
        password2 = findViewById(R.id.confirmPassword);
        btnSignUp = findViewById(R.id.signUp);
        tvSignIn = findViewById(R.id.signIn);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String email = emailId.getText().toString();
                String pwd1 = password1.getText().toString();
                String pwd2 = password2.getText().toString();

                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else if (pwd1.isEmpty()){
                    password1.setError("Please enter password");
                    password1.requestFocus();
                }
                else if(name.isEmpty()){
                    username.setError("Please enter username");
                    username.requestFocus();
                }
                else if(pwd2.isEmpty()){
                    password2.setError("Please confirm password");
                    password2.requestFocus();
                }
                else if (name.isEmpty() && email.isEmpty() && pwd1.isEmpty() && pwd2.isEmpty()){
                    Toast.makeText(SignUpPage.this, "Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else if (!(name.isEmpty() && email.isEmpty() && pwd1.isEmpty() && pwd2.isEmpty())){
                     mFirebaseAuth.createUserWithEmailAndPassword(email, pwd1).addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if(!task.isSuccessful()){
                                 Toast.makeText(SignUpPage.this, "SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                             }
                             else{
                                 startActivity(new Intent(SignUpPage.this,MainActivity2.class));
                             }
                         }
                     });
                }
                else{
                    Toast.makeText(SignUpPage.this, "Error Occurred!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpPage.this, MainActivity.class));
            }
     });

    }
}