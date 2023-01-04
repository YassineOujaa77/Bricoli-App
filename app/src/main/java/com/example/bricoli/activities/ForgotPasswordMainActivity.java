package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.bricoli.R;

import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;

import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bricoli.models.Client;
import com.example.bricoli.models.Worker;
import com.example.bricoli.retrofit.RetrofitService;
import com.example.bricoli.retrofit.UserApi;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForgotPasswordMainActivity extends AppCompatActivity {
    private Button tosmsactivity;
    private String theusernumbertocheck;
    private TextView thewrongnumbermessage;
    private EditText phone;
    public static String verificationCode;
    public static Client localclient;
    public static Worker localworker;




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
    public int isitWorkerOrClient(String theusernumber,Worker myworkerifound,Client myclientifound){
        int user_type = 4;//0 c client 1 C worker 2 c null on a aucun utilisateur avec ce telephone
        final Boolean[] isitClient = new Boolean[1];
        final Boolean[] isitWorker = new Boolean[1];
        RetrofitService retrofit = new RetrofitService();
        UserApi ziyad = retrofit.getRetrofit().create(UserApi.class);
        Call<Worker> worker=ziyad.getWorkerByPhoneNumber(theusernumber);
        Call<Client> client=ziyad.getClientByPhoneNumber(theusernumber);
        client.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                Client myClient = response.body();
                if(myClient==null)
                {
                    isitClient[0] =false;
                }else
                    ForgotPasswordMainActivity.localclient=myClient;
            }
            @Override
            public void onFailure(Call<Client> call, Throwable t) {
            }
        });
        worker.enqueue(new Callback<Worker>() {
            @Override
            public void onResponse(Call<Worker> call, Response<Worker> response) {
                Worker myWorker = response.body();
                if(myWorker==null) {
                    isitWorker[0] = false;
                }else {
                    ForgotPasswordMainActivity.localworker=myWorker;
                }
            }
            @Override
            public void onFailure(Call<Worker> call, Throwable t) {

            }
        });
        if(isitClient[0]==true){
            user_type=0;
            
        }
        if(isitWorker[0]==true){
            user_type=1;
            
        }
        if (isitClient[0]==false && isitWorker[0]==false){
            user_type=3;
        }
        return user_type;
        
        
    }

    public void opensmsactivity(String theusernumbertocheck){
            Intent intent = new Intent(this, ForgotpasswordSmsActivity.class);
            startActivity(intent);
            sendSMS(theusernumbertocheck);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_main);

        ActivityCompat.requestPermissions(ForgotPasswordMainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        phone   = (EditText) findViewById(R.id.Phone);


        thewrongnumbermessage=(TextView) findViewById(R.id.thewrongnumbermessage);
        tosmsactivity = (Button) findViewById(R.id.tosms);
        tosmsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Client myclientifound=null;
                Worker myworkerifound = null;

                theusernumbertocheck=phone.getText().toString();
                int type=isitWorkerOrClient(theusernumbertocheck,myworkerifound,myclientifound);
                if (type==0 || type==1) {
                    if(type==0) {
                        //passer en parametres un client
                        opensmsactivity(theusernumbertocheck);
                    }
                    if(type==1){
                        //passer en parametres un worker
                        opensmsactivity(theusernumbertocheck);
                    }

                }else
                    thewrongnumbermessage.setVisibility(View.VISIBLE);
            }
        });



    }

}



