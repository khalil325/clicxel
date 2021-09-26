package com.example.clicxel;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;

import android.view.LayoutInflater;

import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    Button createAccountBtn, loginBtn, forget_password_btn, google_signIn, phone_signIn;
    EditText username, password;
    FirebaseAuth firebaseAuth;
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        reset_alert = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();

        createAccountBtn = findViewById(R.id.createAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), signup.class));
            }
        });

        username = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);
        forget_password_btn = findViewById(R.id.forget_password_btn);
        google_signIn=findViewById(R.id.google_signIn);
        phone_signIn= findViewById(R.id.phone_signIn);


        forget_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v){
                //start alertdailogue
               //AlertDialog.Builder reset_alert= new AlertDialog.Builder(getApplicationContext());
                View view = inflater.inflate(R.layout.reset_pop, null);
                reset_alert.setTitle("Reset Forgot Password?")
                        .setMessage("Enter Your Email to get Password Reset Link")
                        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //validate the email address
                               EditText email = view.findViewById(R.id.rest_email_pop);
                               if (email.getText().toString().isEmpty()) {
                                   email.setError("Required Field");
                                   return;
                               }
                                //send the reset link
                               firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                   @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(login.this, "Reset Email Sent", Toast.LENGTH_SHORT).show();
                                    }
                                 }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                   }
                                });
                            }
                        }).setNegativeButton("Cancel", null)
                        .setView(view)
                        .create().show();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //extract
                if (username.getText().toString().isEmpty()) {
                    username.setError("Email is Missing");
                    return;
                }

                if (password.getText().toString().isEmpty()) {
                    password.setError("Password is Missing");
                    return;
                }
                //data is valid
                //login user

                firebaseAuth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //when login successfully
                        startActivity(new Intent(getApplicationContext(), biometric.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        google_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), google.class);
                startActivity(intent);
                return;
            }
        });

        phone_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), phoneNo.class);
                startActivity(intent);
                return;
            }
        });

    }


    //if user already logged in it went to the edit picture and taken image page



    }


