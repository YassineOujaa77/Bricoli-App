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
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Worker;

public class ForgotpasswordSmsActivity extends AppCompatActivity {
    private Button tochangepass;
    String verificationCode;
    EditText veriCode;
    TextView wrongcodemessage;

    public void opentochangepass(Client client,Worker worker){
        Intent intent=new Intent(this, ForgotPasswordChangePassword.class);
        intent.putExtra("client",client);
        intent.putExtra("worker",worker);
        startActivity(intent);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword_sms);
        veriCode=(EditText) findViewById(R.id.verification);

        Client myclient = (Client) getIntent().getSerializableExtra("client");
        Intent intent = getIntent();
        String sms=intent.getStringExtra("Codedeverification");
        Worker myworker = (Worker) getIntent().getSerializableExtra("worker");

        wrongcodemessage=(TextView) findViewById(R.id.messagewrongcode);
        tochangepass = (Button) findViewById(R.id.changepswd);
        tochangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Code=veriCode.getText().toString();
                if(Code.equals(sms)){
                    System.out.println(sms);
                    System.out.println("sf rah khddam hna");
                    opentochangepass(myclient,myworker);
                }else{
                    wrongcodemessage.setVisibility(View.VISIBLE);

                }

            }
        });

    }
}