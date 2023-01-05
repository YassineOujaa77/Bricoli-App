package com.example.bricoli.activities;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bricoli.R;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Worker;
import com.example.bricoli.retrofit.RetrofitService;
import com.example.bricoli.retrofit.UserApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    //to update in database in every login
    String userdevicetoken;//this is the device token for the user in the signup activity




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // fcm settings for perticular user



        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        userdevicetoken= task.getResult();
                    }
                });


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
                    RetrofitService retrofit = new RetrofitService();
                    UserApi ziyad = retrofit.getRetrofit().create(UserApi.class);

                    Call<Client> client=ziyad.getClientByPhoneNumber("0000");
                    client.enqueue(new Callback<Client>() {
                        @SuppressLint("SuspiciousIndentation")
                        @Override
                        public void onResponse(Call<Client> call, Response<Client> response) {
                            Client myClient = response.body();
                            if(myClient==null)
                            {
                                System.out.println("failed to find this client");

                            }else {
                                System.out.println("found client");

                            }
                        }
                        @Override
                        public void onFailure(Call<Client> call, Throwable t) {
                            System.out.println("failed to work with client");
                        }
                    });
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