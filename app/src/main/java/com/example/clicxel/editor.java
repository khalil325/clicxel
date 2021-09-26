package com.example.clicxel;

import static android.view.WindowManager.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;

public class editor extends AppCompatActivity {
    Button btPick;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_editor);
        btPick = findViewById(R.id.bt_pick);
        imageView = findViewById(R.id.image_view);

        btPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create method
                checkPermission();


            }
        });
    }
    private void checkPermission() {
        //Initialize Permission
        int permission = ActivityCompat.checkSelfPermission(editor.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //check condition
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            //When device version is greater than equal to version 10
            //Create method
            pickImage();

        }
        else{
            //When device version is below version 10
            //check condition
            if (permission != PackageManager.PERMISSION_GRANTED){
                //When permission is not granted
                //Request permission
                ActivityCompat.requestPermissions(editor.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);
            }
            else{
                //When permisiion is granted
                //Call method
                pickImage();
            }


        }

    }

    private void pickImage(){
        //Initialize intent
        Intent intent = new Intent(Intent.ACTION_PICK);
        //Set type
        intent.setType("image/*");
        //Start activity for result
        startActivityForResult(intent,100);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Check Condition
        if (requestCode == 100 && grantResults.length>0 && grantResults[0]
                == PackageManager.PERMISSION_GRANTED){
            //When permission is granted
            //Call method
            pickImage();
        }
        else{
            //When permission id denied
            Toast.makeText(getApplicationContext(),
                    "Permission Denied.",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Check Condition
        if (resultCode == RESULT_OK){
            //When result is ok
            //Initialize uri
            Uri uri = data.getData();
            switch (requestCode){
                case 100:
                    //When request code is equal to 100
                    //Initialize intent
                    Intent intent = new Intent(editor.this,
                            DsPhotoEditorActivity.class);
                    //Set Data
                    intent.setData(uri);
                    //Set output Directory name
                    intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY,
                            "Images");
                    //Set toolbar Color
                    intent.putExtra(DsPhotoEditorConstants.DS_TOOL_BAR_BACKGROUND_COLOR,
                            Color.parseColor("#FF6200EE"));
                    //Set background Color
                    intent.putExtra(DsPhotoEditorConstants.DS_MAIN_BACKGROUND_COLOR,
                            Color.parseColor("#FFFFFF"));
                    //Hide Tools
                    intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE,
                            new int[]{DsPhotoEditorActivity.TOOL_WARMTH,
                                    DsPhotoEditorActivity.TOOL_PIXELATE});
                    //Start activity for result
                    startActivityForResult(intent,101);
                    break;
                case 101:
                    //When request is equal to 100
                    //Set image on image view
                    imageView.setImageURI(uri);
                    //Display toast
                    Toast.makeText(getApplicationContext(),
                            "Photo Saved",Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }
}
