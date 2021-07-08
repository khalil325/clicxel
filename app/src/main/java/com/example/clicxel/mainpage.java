package com.example.clicxel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        Button signup = findViewById(R.id.signup);
        Button login = findViewById(R.id.login);

        ///////////////SIGNUP////////
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), signup.class);
                startActivity(intent);
                return;
            }
        });
////////////////SLOGIN////////
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), login.class);
                startActivity(intent);
                return;
            }
        });
    }}


