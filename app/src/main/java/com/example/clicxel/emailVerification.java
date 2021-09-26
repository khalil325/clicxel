package com.example.clicxel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class emailVerification extends AppCompatActivity {
    Button verifyEmailBtn, logout;
    TextView verifyMsg, emailtext;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        auth=FirebaseAuth.getInstance();
        emailtext=findViewById(R.id.emailtext);
        logout = findViewById(R.id.logoutBtn);
        verifyMsg=findViewById(R.id.verifyEmailMsg);
        verifyMsg=findViewById(R.id.verifyEmailMsg);
        verifyEmailBtn=findViewById(R.id.verifyEmailBtn);
        if(!auth.getCurrentUser().isEmailVerified()){
            emailtext.setVisibility(View.VISIBLE);
            verifyEmailBtn.setVisibility(View.VISIBLE);
            verifyMsg.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
        }
        verifyEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(emailVerification.this, "Verification Link Sent on Email.", Toast.LENGTH_LONG).show();
                        emailtext.setVisibility(View.GONE);
                        verifyEmailBtn.setVisibility(View.GONE);
                        verifyMsg.setVisibility(View.GONE);
                        logout.setVisibility(View.VISIBLE);


                    }
                });
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),phoneNo.class));
                finish();
            }
        });

    }
}