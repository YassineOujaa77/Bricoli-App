package com.example.bricoli;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button forgot = (Button) findViewById(R.id.forgotPassword);
        forgot.setPaintFlags(forgot.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        Button sign = (Button) findViewById(R.id.signUp);
        sign.setPaintFlags(sign.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
    }
}