package com.example.bricoli.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.bricoli.R;
import com.example.bricoli.adapters.HistoryAdapter;
import com.example.bricoli.models.History;
import com.example.bricoli.models.Postulation;
import com.example.bricoli.retrofit.PostulationApi;
import com.example.bricoli.retrofit.RetrofitServiceForPostulation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryOffreActivity extends AppCompatActivity {

    int images[] = {R.drawable.man,R.drawable.man};
    String nomComple[]={"Ziyad BOUSSERRHINE","Saad Tebaa","Yassine Oujaa"};
    String ville[]={"Rabat","Méknes","Kénitra"};
    String notes[]={"4.5 (520)","4.5 (520)","4.5 (520)"};
    String dates[]={"2 Days","2 Days","2 Days"};
    String descriptions[]={"je suis entrain de chercher un plombier pour réparation d’une ....","je suis entrain de chercher un plombier pour réparation d’une ....","je suis entrain de chercher un plombier pour réparation d’une ...."};
    ListView historylist;
    ArrayList<History> histories;
    List<Postulation> postulations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_offre);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);



        historylist = findViewById(R.id.historylist);
        historylist.setBackgroundColor(Color.WHITE);
        postulations=new ArrayList<Postulation>();


        RetrofitServiceForPostulation retrofit = new RetrofitServiceForPostulation();
        PostulationApi postulation=retrofit.getRetrofit().create(PostulationApi.class);
        postulation.getPostulationByWorkerIdAndState(1l,"FINISHED").enqueue(new Callback<List<Postulation>>() {
            @Override
            public void onResponse(Call<List<Postulation>> call, Response<List<Postulation>> response) {
                postulations=response.body();
                //Log.d("test","*************************** "+postulations.size());
                if(postulations.size()!=0)
                {
                    String profils[]=new String[postulations.size()];
                    String fullName[]=new String[postulations.size()];
                    String marks[]=new String[postulations.size()];
                    String periods[]=new String[postulations.size()];
                    String desc[]=new String[postulations.size()];
                    String cities[]=new String[postulations.size()];
                    for(int i=0;i<postulations.size();i++)
                    {
                        profils[i]=postulations.get(i).getOffer().getClient().getPhoto();
                        fullName[i]=postulations.get(i).getOffer().getClient().getFullName();
                        float b=postulations.get(i).getOffer().getClient().getNumberOfRating();
                        float note=postulations.get(i).getOffer().getClient().getSommeRating()/b;
                        marks[i]=Float.toString(note);
                        int duration=postulations.get(i).getDuration();
                        periods[i]=duration+" Days";
                        desc[i]=postulations.get(i).getOffer().getDescription();
                        cities[i]="Rabat";
                    }
                    histories=new ArrayList<History>();
                    for(int i=0;i<postulations.size();i++)
                    {
                        History history=new History(profils[i],fullName[i],cities[i],marks[i],periods[i],desc[i]);
                        histories.add(history);
                    }
                    HistoryAdapter historyAdapter=new HistoryAdapter(getApplicationContext(),histories);
                    historylist.setAdapter(historyAdapter);
                    historylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            startActivity(new Intent(HistoryOffreActivity.this,PostDetailsWaitingActivity.class));
                        }
                    });










                }
                else
                {

                }





            }

            @Override
            public void onFailure(Call<List<Postulation>> call, Throwable t) {
                Log.d("test","*************************** Failure");
            }
        });









        // initialize
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.history);

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