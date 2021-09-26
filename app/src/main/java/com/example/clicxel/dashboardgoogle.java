package com.example.clicxel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import android.os.Bundle;

public class dashboardgoogle extends AppCompatActivity {
    ImageView img;
    TextView name, email;
    GoogleSignInClient mGoogleSignInClient;
    Button logoutbtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardgoogle);
        img= findViewById(R.id.pimage);
        name= findViewById(R.id.nametext);
        email= findViewById(R.id.emailtext);
        logoutbtn= findViewById(R.id.button);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        name.setText(account.getDisplayName());
        email.setText(account.getEmail());
        Glide.with(this).load(account.getPhotoUrl()).into(img);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                startActivity(new Intent(getApplicationContext(),biometric.class));
                //deleteAppData();
                finish();

            }

        });
    }

    public void abc(){
        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
            ((ActivityManager)getApplicationContext().getSystemService(ACTIVITY_SERVICE))
                    .clearApplicationUserData(); // note: it has a return value!
        } else {
            // use old hacky way, which can be removed
            // once minSdkVersion goes above 19 in a few years.
        }
    }

    private void deleteAppData() {
        try {
            // clearing app data
            String packageName = getApplicationContext().getPackageName();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear "+packageName);

        } catch (Exception e) {
            e.printStackTrace();
        } }

}




//
//     public void abc(){
//        FirebaseAuth firebaseAuth;
//    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
//        @Override
//        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//            if (firebaseAuth.getCurrentUser() == null) {
//                //Do anything here which needs to be done after signout is complete
//                //signOutComplete();
//                startActivity(new Intent(getApplicationContext(),google.class));
//                finish();
//                Toast.makeText(dashboardgoogle.this, "Log out Successfully", Toast.LENGTH_SHORT).show();
//            } else { } } };
//                // Init and attach
//         firebaseAuth = FirebaseAuth.getInstance();
//                firebaseAuth.addAuthStateListener(authStateListener);
//                // Call signOut()
//         firebaseAuth.signOut();
//         FirebaseAuth.getInstance().signOut();
//
//
//
//            }

