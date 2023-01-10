package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.bricoli.R;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Worker;
import com.example.bricoli.retrofit.ClientApi;
import com.example.bricoli.retrofit.RetrofitServiceForClient;
import com.example.bricoli.retrofit.RetrofitServiceForWorker;
import com.example.bricoli.retrofit.WorkerApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeRoleActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_role);
        SharedPreferences preferences = getSharedPreferences("contenu", MODE_PRIVATE);
        String state=preferences.getString("role","default");
        Long idUser=preferences.getLong("IdUser",-1l);

        RadioButton client=findViewById(R.id.radio_button_client);
        RadioButton worker=findViewById(R.id.radio_button_worker);
        Button btnSave=findViewById(R.id.btnSave);
        if(state.equals("client"))
        {
            client.setChecked(true);
        }
        else if(state.equals("worker"))
        {
             worker.setChecked(true);
        }
        /*btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitServiceForWorker retrofitWorker=new RetrofitServiceForWorker();
                RetrofitServiceForClient retrofitClient=new RetrofitServiceForClient();
                ClientApi cliente=retrofitClient.getRetrofit().create(ClientApi.class);
                WorkerApi workeer=retrofitWorker.getRetrofit().create(WorkerApi.class);


                if(client.isChecked()==true && state.equals("worker"))
                {
                    workeer.getWorkerById(idUser).enqueue(new Callback<Worker>() {
                        @Override
                        public void onResponse(Call<Worker> call, Response<Worker> response) {
                            Worker worker=response.body();
                            workeer.deleteWorker(idUser).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    Log.d("deleteWorker","********************WorkerDeleted");
                                    Client client=new Client();
                                    client.setCin(worker.getCin());
                                    client.setPassword(worker.getPassword());
                                    client.setAddress(worker.getAddress());
                                    client.setSommeRating(worker.getSommeRating());
                                    client.setNumberOfRating(worker.getNumberOfRating());
                                    client.setPhoto(worker.getPhoto());
                                    client.setFullName(worker.getFullName());
                                    client.setWorkerField(worker.getWorkerField());
                                    client.setPhone(worker.getPhone());
                                    client.setToken("jiu");
                                    client.setOffers(null);


                                    cliente.addClient(client).enqueue(new Callback<Client>() {
                                        @Override
                                        public void onResponse(Call<Client> call, Response<Client> response) {
                                            Log.d("addClient","**********************ClientAdded");
                                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                        }

                                        @Override
                                        public void onFailure(Call<Client> call, Throwable t) {

                                        }
                                    });






                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<Worker> call, Throwable t) {

                        }
                    });




                }
                else if(worker.isChecked()==true && state.equals("client"))
                {
                    cliente.getClientById(idUser).enqueue(new Callback<Client>() {
                        @Override
                        public void onResponse(Call<Client> call, Response<Client> response) {
                            Client client=response.body();
                            cliente.deleteClient(idUser).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    Log.d("deleteClient","**************************ClientDeleted");
                                    Worker worker=new Worker();
                                    worker.setCin(client.getCin());
                                    worker.setPassword(client.getPassword());
                                    worker.setAddress(client.getAddress());
                                    worker.setSommeRating(client.getSommeRating());
                                    worker.setNumberOfRating(client.getNumberOfRating());
                                    worker.setPhoto(client.getPhoto());
                                    worker.setFullName(client.getFullName());
                                    worker.setWorkerField(client.getWorkerField());
                                    worker.setPhone(client.getPhone());
                                    worker.setToken(client.getToken());
                                    worker.setJobs(null);
                                    worker.setPostulations(null);



                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<Client> call, Throwable t) {

                        }
                    });

                }
            }
        });


        




        Log.d("Etat","********************************** "+state);
        Log.d("Id","************************* "+idUser);









*/
    }
}