package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bricoli.R;

public class ForgotpasswordSmsActivity extends AppCompatActivity {
    private Button tochangepass;
    String verificationCode;
    EditText veriCode;
    TextView wrongcodemessage;

    public void opentochangepass(){
        Intent intent=new Intent(this, ForgotPasswordChangePassword.class);
        startActivity(intent);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword_sms);
        verificationCode=ForgotPasswordMainActivity.verificationCode.toString();
        veriCode=(EditText) findViewById(R.id.verification);

        wrongcodemessage=(TextView) findViewById(R.id.messagewrongcode);
        tochangepass = (Button) findViewById(R.id.changepswd);
        tochangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Code=veriCode.getText().toString();
                if(Code.equals(verificationCode)){
                    opentochangepass();
                }else{
                    wrongcodemessage.setVisibility(View.VISIBLE);

                }

            }
        });

    }
}