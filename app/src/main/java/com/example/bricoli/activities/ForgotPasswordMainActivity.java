package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.bricoli.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;

import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;


public class ForgotPasswordMainActivity extends AppCompatActivity {
    private Button tosmsactivity;
    private String theusernumber;
    private String theusernumbertocheck;
    private TextView thewrongnumbermessage;
    private EditText phone;
    public static String verificationCode;

    public void sendSMS(String theusernumber){
        Random rand = new Random(); //instance of random class
        int upperbound = 1000000;
        //generate random values from 0-999999
        int int_random = rand.nextInt(upperbound);
        int message = int_random;
        verificationCode=String.valueOf(message);

        String number = theusernumber;
        SmsManager mySmsManager = SmsManager.getDefault();
        mySmsManager.sendTextMessage(number,null, String.valueOf(message), null, null);
    }

    public void opensmsactivity(String theusernumber,String theusernumbertocheck,TextView thewrongnumbermessage){
        if(theusernumber.equals(theusernumbertocheck)) {
            Intent intent = new Intent(this, ForgotpasswordSmsActivity.class);
            startActivity(intent);
            sendSMS(theusernumber);

        }else {
            thewrongnumbermessage.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_main);
        ActivityCompat.requestPermissions(ForgotPasswordMainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        phone   = (EditText) findViewById(R.id.Phone);

        theusernumber="0710655578";
        theusernumber=theusernumber.toString();
        thewrongnumbermessage=(TextView) findViewById(R.id.thewrongnumbermessage);
        tosmsactivity = (Button) findViewById(R.id.tosms);
        tosmsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theusernumbertocheck=phone.getText().toString();
                opensmsactivity(theusernumber,theusernumbertocheck,thewrongnumbermessage);
                System.out.println(theusernumbertocheck);
            }
        });


    }

}



