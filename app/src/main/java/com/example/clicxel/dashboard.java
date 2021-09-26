package com.example.clicxel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class dashboard extends AppCompatActivity {
    ImageButton camerabtn, editbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        camerabtn = findViewById(R.id.camera);
        editbtn = findViewById(R.id.editbtn);

        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), filter.class);
                startActivity(intent);
                return;
            }
        });

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), editor.class);
                startActivity(intent);
                return;
            }
        });
    }
}