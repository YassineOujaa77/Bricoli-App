package com.example.bricoli.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bricoli.R;
import com.example.bricoli.util.FcmNotificationsSender;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class ForgotPasswordChangePassword extends AppCompatActivity {
    private Button submitpassword;
    EditText firstpass,verificationpass;
    String token;
    public void openlogin(){
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_change_password);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        token= task.getResult();
                    }
                });

        firstpass=(EditText) findViewById(R.id.newpassword);
        verificationpass=(EditText) findViewById(R.id.confirmpass);
         submitpassword = (Button) findViewById(R.id.submitpassword);
        submitpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!firstpass.getText().toString().isEmpty() && !verificationpass.getText().toString().isEmpty()) {
                    if (firstpass.getText().toString().equals(verificationpass.getText().toString())) {
                        openlogin();
                        FcmNotificationsSender notificationsSender = new FcmNotificationsSender(token, "Operation Accomplie", "Vous avez changé avec succès votre mot de pass", getApplicationContext(), ForgotPasswordChangePassword.this);
                        notificationsSender.SendNotifications();
                    } else {// les deux mots de pass sont pas egaux
                        Toast.makeText(ForgotPasswordChangePassword.this, "Les mots de pass entrés ne sont pas égaux", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(ForgotPasswordChangePassword.this, "Entrez les nouveaux mots de pass", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}