package com.example.bricoli.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bricoli.R;

public class LoginActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button forgot = (Button) findViewById(R.id.forgotPassword);
        forgot.setPaintFlags(forgot.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Button sign = (Button) findViewById(R.id.signUp);
        sign.setPaintFlags(sign.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Button login = findViewById(R.id.login);
        EditText username = findViewById(R.id.phone);
        EditText password = findViewById(R.id.password);
        CheckBox rememberMe = (CheckBox) findViewById(R.id.rememberMe);


        // verify if already login

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String remeberMeCheckbox = preferences.getString("remember", "");
        String role = preferences.getString("role","");
        // Shared Prefrences
        SharedPreferences.Editor editor = preferences.edit();

        if (remeberMeCheckbox.equals("true")) {
            if(role.equals("client")){
                Intent intent = new Intent(getApplicationContext(), ClientHomeActivity.class);
                startActivity(intent);
            }else{
                Intent intent = new Intent(getApplicationContext(), WorkerHomeActivity.class);
                startActivity(intent);
            }

        } else {
            Toast.makeText(this, "Please login", Toast.LENGTH_SHORT).show();
        }

        // event listener for log in click

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("client") && password.getText().toString().equals("client")) {
                    editor.putString("role", "client");
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, ClientHomeActivity.class);
                    startActivity(intent);
                } else if (username.getText().toString().equals("worker") && password.getText().toString().equals("worker")) {
                    editor.putString("role", "worker");
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, WorkerHomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Phone or password incorrect.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent1);

            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginActivity.this, ForgotPasswordMainActivity.class);
                startActivity(intent1);

            }
        });




        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {

                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "CHECKED ", Toast.LENGTH_SHORT).show();

                } else if (!buttonView.isChecked()) {

                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "CHECKED ", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}