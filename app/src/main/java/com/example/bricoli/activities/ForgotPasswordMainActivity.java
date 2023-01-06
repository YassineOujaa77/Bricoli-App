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
import com.example.bricoli.retrofit.RetrofitServiceForWorker;
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


    public int sendSMS(String theusernumber){
        Random rand = new Random(); //instance of random class
        int upperbound = 1000000;
        //generate random values from 0-999999
        int int_random = rand.nextInt(upperbound);
        int message = int_random;
        String number = theusernumber;
        SmsManager mySmsManager = SmsManager.getDefault();
        mySmsManager.sendTextMessage(number,null, String.valueOf(message), null, null);
        return message;

    }

    public void opensmsactivity(String theusernumbertocheck,Client client,Worker worker,Long idclient){
            Intent intent = new Intent(this, ForgotpasswordSmsActivity.class);
            intent.putExtra("client",client);
            intent.putExtra("worker",worker);
            String idc=idclient+"";
            int sms=sendSMS(theusernumbertocheck);
            intent.putExtra("idclient",idc);
            intent.putExtra("Codedeverification",sms+"");
            startActivity(intent);


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
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                theusernumbertocheck=phone.getText().toString();
                RetrofitService retrofit = new RetrofitService();
                UserApi ziyad = retrofit.getRetrofit().create(UserApi.class);
                //retrofitservice for worker
                RetrofitServiceForWorker workeretrofit = new RetrofitServiceForWorker();
                UserApi chaymaa = workeretrofit.getRetrofit().create(UserApi.class);
                Call<Worker> worker=chaymaa.getWorkerByPhoneNumber(theusernumbertocheck);
                Call<Client> client=ziyad.getClientByPhoneNumber(theusernumbertocheck);
                ///////////////
                client.enqueue(new Callback<Client>() {
                    @SuppressLint("SuspiciousIndentation")
                    @Override
                    public void onResponse(Call<Client> call, Response<Client> response) {
                        Client myClient = response.body();
                        if(myClient==null)
                        {
                            thewrongnumbermessage.setVisibility(View.VISIBLE);
                            System.out.println("failed to find this client");

                        }else {

                            System.out.println(myClient.getUserId());
                            System.out.println("**********************************************************************************************************************************");
                            opensmsactivity(theusernumbertocheck,myClient,null,myClient.getUserId());
                            System.out.println("found client");

                        }
                    }
                    @Override
                    public void onFailure(Call<Client> call, Throwable t) {
                        System.out.println("failed to work with client");
                    }
                });
                ////////////////
                worker.enqueue(new Callback<Worker>() {
                    @Override
                    public void onResponse(Call<Worker> call, Response<Worker> response) {
                        Worker myWorker = response.body();
                        if(myWorker==null) {
                            thewrongnumbermessage.setVisibility(View.VISIBLE);
                        }else {
                            opensmsactivity(theusernumbertocheck,null,myWorker,1L);

                        }
                    }
                    @Override
                    public void onFailure(Call<Worker> call, Throwable t) {
                        System.out.println("failed to work with worker");

                    }
                });







            }
        });



    }

}



