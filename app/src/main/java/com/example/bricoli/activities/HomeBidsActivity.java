package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.bricoli.R;
import com.example.bricoli.adapters.AnnoucementAdapter;
import com.example.bricoli.adapters.HomeBidsAdapter;
import com.example.bricoli.models.Annoucement;

import java.util.ArrayList;

public class HomeBidsActivity extends AppCompatActivity {

    private ListView listView;
    private HomeBidsAdapter homeBidsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_bids);

        listView = (ListView) findViewById(R.id.list_view_for_home_bids);



        ArrayList<Annoucement> annoucementsList = new ArrayList<>();
        annoucementsList.add(new Annoucement("Full name" ,"Rabat","4.5 (250)","250","2 jrs","900 m",R.drawable.userphoto));
        annoucementsList.add(new Annoucement("Full name" ,"Rabat","4.5 (250)","250","2 jrs","900 m",R.drawable.userphoto));
        annoucementsList.add(new Annoucement("Full name" ,"Rabat","4.5 (250)","250","2 jrs","900 m",R.drawable.userphoto));
        annoucementsList.add(new Annoucement("Full name" ,"Rabat","4.5 (250)","250","2 jrs","900 m",R.drawable.userphoto));
        annoucementsList.add(new Annoucement("Full name" ,"Rabat","4.5 (250)","250","2 jrs","900 m",R.drawable.userphoto));

        homeBidsAdapter = new HomeBidsAdapter(this,annoucementsList);
        listView.setAdapter(homeBidsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),WorkerProfilForClientActivity.class));
            }
        });

        



    }
}