package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.bricoli.R;

public class ForgotpasswordSmsActivity extends AppCompatActivity {
    private Button tochangepass;
    public void opentochangepass(){
        Intent intent=new Intent(this, ForgotPasswordChangePassword.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword_sms);


        tochangepass = (Button) findViewById(R.id.changepswd);
        tochangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentochangepass();
            }
        });

    }
}