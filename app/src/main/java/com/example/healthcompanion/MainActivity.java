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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText username,emailId,password;
    private Button btnSignIn;
    private TextView tvSignUp;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private TextView tvForgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Health Companion");

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.enterEmail);
        username = findViewById(R.id.username);
        password = findViewById(R.id.enterPassword);
        btnSignIn = findViewById(R.id.signIn);
        tvSignUp = findViewById(R.id.signUp);
        tvForgotPassword = findViewById(R.id.forgotPassword);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser!= null ){
                    Toast.makeText(MainActivity.this,"You are logged In",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                }

            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String email = emailId.getText().toString();
                String pwd1 = password.getText().toString();



                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else if (pwd1.isEmpty()){
                    password.setError("Please enter password");
                    password.requestFocus();
                }
                else if(name.isEmpty()){
                    username.setError("Please enter username");
                    username.requestFocus();
                }

                else if (name.isEmpty() && email.isEmpty() && pwd1.isEmpty()){
                    Toast.makeText(MainActivity.this, "Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else if (!(name.isEmpty() && email.isEmpty() && pwd1.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd1).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent main1 =new Intent(MainActivity.this,MainActivity2.class);
                                startActivity(main1);
                                }


                            }

                    });
                }
                else{
                    Toast.makeText(MainActivity.this, "Error Occurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgotPasswordPage.class));
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, SignUpPage.class));
            }



        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}