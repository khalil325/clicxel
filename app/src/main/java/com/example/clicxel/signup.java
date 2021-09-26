package com.example.clicxel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    EditText registerFullName,registerEmail,registerPassword, registerConfpass;
    Button registerUserBtn, gotoLogin;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        registerFullName=findViewById(R.id.registerFullName);
        registerEmail=findViewById(R.id.registerEmail);
        registerPassword=findViewById(R.id.registerPassword);
        registerConfpass=findViewById(R.id.confPassword);
        registerUserBtn=findViewById(R.id.registerBtn);
        gotoLogin=findViewById(R.id.gotoLogin);
        fAuth = FirebaseAuth.getInstance();


        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();
            }
        });
        //extract data
        registerUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = registerFullName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String confPass = registerConfpass.getText().toString();

                if (fullName.isEmpty()) {
                    registerFullName.setError("Full Name is required");
                    return;
                }

                if (email.isEmpty()) {
                    registerEmail.setError("Email is required");
                    return;
                }

                if (password.isEmpty()) {
                    registerPassword.setError("Password is required");
                    return;
                }
                if (confPass.isEmpty()) {
                    registerConfpass.setError("Confirm Password is required");
                    return;
                }

                if (!password.equals(confPass)) {
                    registerConfpass.setError("Password doesn't match");
                    return;
                }

                //valid data
                //Toast.makeText(Register.this, "Data Validated", Toast.LENGTH_SHORT).show();
                fAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //send user to next page
                        startActivity(new Intent(getApplicationContext(), emailVerification.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}