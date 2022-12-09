package com.example.bricoli.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bricoli.R;
import com.example.bricoli.adapters.HistoryAdapter;
import com.example.bricoli.models.History;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HistoryOffreActivity extends AppCompatActivity {

    int images[] = {R.drawable.man,R.drawable.man,R.drawable.man};
    String nomComple[]={"Ziyad BOUSSERRHINE","Saad Tebaa","Yassine Oujaa"};
    String ville[]={"Rabat","Méknes","Kénitra"};
    String notes[]={"4.5 (520)","4.5 (520)","4.5 (520)"};
    String dates[]={"2 Days","2 Days","2 Days"};
    String descriptions[]={"je suis entrain de chercher un plombier pour réparation d’une ....","je suis entrain de chercher un plombier pour réparation d’une ....","je suis entrain de chercher un plombier pour réparation d’une ...."};
    ListView historylist;
    ArrayList<History> histories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_offre);

        historylist = findViewById(R.id.historylist);
        historylist.setBackgroundColor(Color.WHITE);
        histories=new ArrayList<History>();
        for(int i=0;i<images.length;i++)
        {
            History history=new History(images[i],nomComple[i],ville[i],notes[i],dates[i],descriptions[i]);
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