package com.example.bricoli.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bricoli.R;
import com.example.bricoli.adapters.CurrentBidsAdapter;

public class CurrentBidsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_bids);
        ListView lisOfBids=findViewById(R.id.listOfBids);

        int []Photos={R.drawable.user,R.drawable.user,R.drawable.user};
        String []Villes={"Rabat","Rabat","Rabat"};
        String []Noms={"Nom Complet","Nom Complet","Nom Complet"};
        String []Notes={"4.5 (520)","4.5 (520)","4.5 (520)"};
        String []Prices={"200 DH","200 DH","200 DH"};
        String []Periodes={"2 jrs","2 jrs","2 jrs"};
        String []Distances={"900m","900m","900m"};
        String []Etats={"waiting","declined","accepted"};
        CurrentBidsAdapter adapter=new CurrentBidsAdapter(getApplicationContext(),Photos,Villes,Noms,Notes,Prices,Periodes,Distances,Etats);
        lisOfBids.setAdapter(adapter);
        lisOfBids.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                View v= LayoutInflater.from(getApplicationContext()).inflate(R.layout.current_bid_card,null);
                Button btn=view.findViewById(R.id.btnTest);
                if(btn.getText()=="Waiting")
                {
                    Intent intent=new Intent(CurrentBidsActivity.this,PostDetailsWaitingActivity.class);
                    startActivity(intent);

                }
                else if(btn.getText()=="Accepted")
                {
                    Intent intent=new Intent(CurrentBidsActivity.this, JobDetailsForClientActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}