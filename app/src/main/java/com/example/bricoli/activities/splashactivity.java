package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bricoli.R;


public class splashactivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);

       /* VideoView myvideo=findViewById(R.id.videosplash);
        String stringvideopath= "android.resource://"+ getPackageName()+ "/"+R.raw.splashscreenvideo;
        Uri uri = Uri.parse(stringvideopath);
        myvideo.setVideoURI(uri);
        myvideo.start();*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeintent = new Intent(splashactivity.this,HomeBidsActivity.class);
                startActivity(homeintent);
                finish();
            }
        },SPLASH_TIME_OUT);


    }



}