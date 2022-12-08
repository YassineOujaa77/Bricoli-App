package com.example.bricoli.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CurrentBidsActivity extends AppCompatActivity {


    int []Photos={R.drawable.user,R.drawable.user,R.drawable.user};
    String []Villes={"Rabat","Rabat","Rabat"};
    String []Noms={"Nom Complet","Nom Complet","Nom Complet"};
    String []Notes={"4.5 (520)","4.5 (520)","4.5 (520)"};
    String []Prices={"200 DH","200 DH","200 DH"};
    String []Periodes={"2 jrs","2 jrs","2 jrs"};
    String []Distances={"900m","900m","900m"};
    String []Etats={"Waiting","Declined","Accepted"};
    ListView list;
    ArrayList<Bid> bids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_bids);
        list=findViewById(R.id.BidsList);
        bids=new ArrayList<Bid>();
        for(int i=0;i<Photos.length;i++)
        {
            Bid bid=new Bid(Photos[i],Villes[i],Noms[i],Notes[i],Prices[i],Periodes[i],Distances[i],Etats[i]);
            bids.add(bid);
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
}