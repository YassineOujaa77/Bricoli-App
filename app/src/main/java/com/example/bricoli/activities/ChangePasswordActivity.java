package com.example.bricoli.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bricoli.R;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Worker;
import com.example.bricoli.retrofit.ClientApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.example.bricoli.retrofit.WorkerApi;
import com.example.bricoli.util.CryptingMethod;
import com.example.bricoli.util.Localisationbyaddress;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    private TextInputEditText password;
    private TextInputEditText passwordConfirmation;
    private Button savebutton;
    String passEnc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        password=(TextInputEditText) findViewById(R.id.passwordInput1);
        passwordConfirmation=(TextInputEditText) findViewById(R.id.verificationpass);
        savebutton=(Button) findViewById(R.id.button);


        Worker worker=new Worker(1L,"JJ202020","nnnnnnn","20/20,Rabat",10L,100,"gggggg","chaymaa elharti","plombier","0000","token");
        Client client= new Client(1L,"30303030","pass","30/40,Marakech",50L,3,""," mohamed benjeloun","","0658601214","this is a token to get ");
        savebutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {

                if (password.getText().toString().equals(passwordConfirmation.getText().toString())){


                    if (!(client==null)){
                    Long myclientid=client.getUserId();

                    RetrofitService retrofit = new RetrofitService();
                    ClientApi myclientapi = retrofit.getRetrofit().create(ClientApi.class);
                    Call<Client> client=myclientapi.getClientById(myclientid);
                    client.enqueue(new Callback<Client>() {
                        @Override
                        public void onResponse(Call<Client> call, Response<Client> response) {
                            Client saad = response.body();
                            CryptingMethod cry = new CryptingMethod();
                            String Passwordencrypted = null;
                            try {
                                passEnc = CryptingMethod.encrypt(password.getText().toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            saad.setPassword(passEnc);
                            Call<Client> client2 = myclientapi.updateClient(saad, saad.getUserId());
                            client2.enqueue(new Callback<Client>() {
                                @Override
                                public void onResponse(Call<Client> call, Response<Client> response) {
                                    Client saad = response.body();
                                    Log.d("tag","i think is updated ****************** "+saad.getPassword());
                                }

                                @Override
                                public void onFailure(Call<Client> call, Throwable t) {

                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<Client> call, Throwable t) {

                        }
                    });}

                    else {
                        Long myworkerid=worker.getUserId();

                        RetrofitService retrofit = new RetrofitService();
                        WorkerApi myworkerapi = retrofit.getRetrofit().create(WorkerApi.class);
                        Call<Worker> worker1=myworkerapi.getWorkerById(myworkerid);
                        worker1.enqueue(new Callback<Worker>() {
                            @Override
                            public void onResponse(Call<Worker> call, Response<Worker> response) {
                                Worker saad = response.body();
                                CryptingMethod cry = new CryptingMethod();
                                String Passwordencrypted = null;
                                try {
                                    passEnc = CryptingMethod.encrypt(password.getText().toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                saad.setPassword(passEnc);
                                Call<Worker> client2 = myworkerapi.updateWorker(saad, saad.getUserId());
                                client2.enqueue(new Callback<Worker>() {
                                    @Override
                                    public void onResponse(Call<Worker> call, Response<Worker> response) {
                                        Worker saad = response.body();
                                        Log.d("tag","i think is updated ****************** "+saad.getPassword());
                                    }

                                    @Override
                                    public void onFailure(Call<Worker> call, Throwable t) {

                                    }
                                });

                            }

                            @Override
                            public void onFailure(Call<Worker> call, Throwable t) {

                            }

                        });
                    }

                }else {
                    Toast.makeText(ChangePasswordActivity.this, "Les mots de pass entrés ne sont pas égaux", Toast.LENGTH_LONG).show();
                }
            }});


    }
}