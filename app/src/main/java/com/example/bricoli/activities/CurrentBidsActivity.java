package com.example.bricoli.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bricoli.R;
import com.example.bricoli.adapters.CurrentBidsAdapter;
import com.example.bricoli.models.Bid;
import com.example.bricoli.models.Postulation;
import com.example.bricoli.models.Worker;
import com.example.bricoli.retrofit.PostulationApi;
import com.example.bricoli.retrofit.RetrofitServiceForPostulation;
import com.example.bricoli.retrofit.RetrofitServiceForWorker;
import com.example.bricoli.retrofit.WorkerApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentBidsActivity extends AppCompatActivity {

    public enum PostulationState {
        ACCEPTED,
        WAITING,
        REJECTED,
        FINISHED
    }


    int []Photos={R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user};
    String []Villes={"Rabat","Rabat","Rabat","Rabat"};

    String []Noms={"Nom Complet","Nom Complet","Nom Complet"};
    String []Notes={"4.5 (520)","4.5 (520)","4.5 (520)"};
    String []Prices={"200 DH","200 DH","200 DH"};
    String []Periodes={"2 jrs","2 jrs","2 jrs"};
    String []Etats={"Waiting","Declined","Accepted"};


    String []Distances={"900m","900m","900m","900m"};


    ListView list;
    ArrayList<Bid> bids;
    List<Postulation> postulations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_bids);
        list=findViewById(R.id.BidsList);
        postulations=new ArrayList<Postulation>();




        RetrofitServiceForWorker worker=new RetrofitServiceForWorker();


        WorkerApi workerApi =worker.getRetrofit().create(WorkerApi.class);


        workerApi.getWorkerById(1l).enqueue(new Callback<Worker>() {
            @Override
            public void onResponse(Call<Worker> call, Response<Worker> response) {
                Worker worker =response.body();


                RetrofitServiceForPostulation retrofit = new RetrofitServiceForPostulation();
                PostulationApi postulation=retrofit.getRetrofit().create(PostulationApi.class);

                postulation.getPostulationsByWorkerId(1l).enqueue(new Callback<List<Postulation>>() {
                    @Override
                    public void onResponse(Call<List<Postulation>> call, Response<List<Postulation>> response) {
                        postulations=response.body();


                        if(postulations.size()!=0)
                        {
                            //Log.d("postulations","****************************** "+postulations.size());
                            String [] imageProfils=new String[postulations.size()];
                            String [] Names=new String[postulations.size()];
                            String [] Notees=new String[postulations.size()];
                            String [] prices=new String[postulations.size()];
                            String []periodes=new String[postulations.size()];
                            String []states=new String[postulations.size()];
                            String []choix=new String[postulations.size()];
                            String []distancee=new String[postulations.size()];
                            String []cities=new String[postulations.size()];

                            for(int i=0;i<postulations.size();i++)
                            {
                                distancee[i]="";
                                cities[i]="Rabat";
                                //Log.d("worker","**************************** "+worker.getFullName());
                                imageProfils[i]=worker.getPhoto();
                                Names[i]=worker.getFullName();
                                float b=worker.getNumberOfRating();
                                float note=worker.getSommeRating()/b;
                                Notees[i]=Float.toString(note);
                                prices[i]= postulations.get(i).getPrice() +" DH";
                                periodes[i]= postulations.get(i).getDuration() +" jrs";
                                states[i]=postulations.get(i).getState();


                                if(states[i].equals("ACCEPTED"))
                                {
                                    choix[i]="Accepted";
                                }
                                else if(states[i].equals("WAITING"))
                                {
                                    choix[i]="Waiting";
                                }
                                else if(states[i].equals("REJECTED"))
                                {
                                    choix[i]="Rejected";
                                }
                                else if(states[i].equals("FINISHED"))
                                {
                                    choix[i]="Finished";
                                }
                            }

                            bids=new ArrayList<Bid>();
                            for(int i=0;i<postulations.size();i++)
                            {
                                Bid bid=new Bid(imageProfils[i],cities[i],Names[i],Notees[i],prices[i],periodes[i],distancee[i],choix[i]);
                                bids.add(bid);
                            }

                            for(int i=0;i<bids.size();i++)
                            {
                                Log.d("bids","**************************** "+bids.get(i).toString());
                            }

                            CurrentBidsAdapter adapter=new CurrentBidsAdapter(getApplicationContext(),bids);
                            list.setAdapter(adapter);
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Button btn=view.findViewById(R.id.btnState);
                                    if(btn.getText()=="Waiting")
                                    {
                                        startActivity(new Intent(CurrentBidsActivity.this,PostDetailsWaitingActivity.class));
                                    }
                                    else if(btn.getText()=="Accepted")
                                    {
                                        startActivity(new Intent(CurrentBidsActivity.this,JobDetailsForClientActivity.class));
                                    }

                                }
                            });

                        }
                        else
                        {

                        }


                    }

                    @Override
                    public void onFailure(Call<List<Postulation>> call, Throwable t) {

                    }
                });




            }

            @Override
            public void onFailure(Call<Worker> call, Throwable t) {

            }
        });










        // initialize
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.current);

        // item from menu selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),WorkerHomeActivity.class));
                        return true;
                    case R.id.current:
                        startActivity(new Intent(getApplicationContext(), CurrentBidsActivity.class));
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), HistoryOffreActivity.class));
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                        return true;
                    default:
                        return false;

                }
            }
        });






    }
}