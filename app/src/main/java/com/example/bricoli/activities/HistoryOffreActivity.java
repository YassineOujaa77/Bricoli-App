package com.example.bricoli.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bricoli.R;
import com.example.bricoli.adapters.HistoryAdapter;

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





    }
}