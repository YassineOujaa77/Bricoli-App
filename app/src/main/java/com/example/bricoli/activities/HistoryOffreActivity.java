package com.example.bricoli.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bricoli.R;
import com.example.bricoli.adapters.HistoryAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HistoryOffreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_offre);

        ListView historylist = findViewById(R.id.historylist);
        historylist.setBackgroundColor(Color.WHITE);

        int images[] = {R.drawable.man,R.drawable.man,R.drawable.man};
        String nomComple[]={"Ziyad BOUSSERRHINE","Saad Tebaa","Yassine Oujaa"};
        String ville[]={"Rabat","Méknes","Kénitra"};
        String notes[]={"4.5 (520)","4.5 (520)","4.5 (520)"};
        String dates[]={"2 Days","2 Days","2 Days"};
        String descriptions[]={"je suis entrain de chercher un plombier pour réparation d’une ....","je suis entrain de chercher un plombier pour réparation d’une ....","je suis entrain de chercher un plombier pour réparation d’une ...."};
        HistoryAdapter historyAdapter=new HistoryAdapter(getApplicationContext(),images,nomComple,ville,notes,dates,descriptions);
        historylist.setAdapter(historyAdapter);


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
                        startActivity(new Intent(getApplicationContext(), ClientHomeActivity.class));
                        return true;
                    case R.id.current:
                        startActivity(new Intent(getApplicationContext(), PostsActuelActivity.class));
                        return true;
                    case R.id.history:
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