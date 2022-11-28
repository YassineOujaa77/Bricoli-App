package com.example.bricoli.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.bricoli.models.Annoucement;
import com.example.bricoli.adapters.AnnoucementAdapter;
import com.example.bricoli.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class WorkerHomeActivity extends AppCompatActivity {
    @BindView(R.id.annoucementList)
    ListView announcementList;
    @OnItemClick(R.id.annoucementList)
    public void onListAnnoucementItemClicked(int position){
        Annoucement annoucementClicked = (Annoucement) announcementList.getItemAtPosition(position);
        Intent announcementIntent = new Intent(WorkerHomeActivity.this, AnnouncementDetailsActivity.class);
        announcementIntent.putExtra("fullName", annoucementClicked.getFullName());
        announcementIntent.putExtra("city", annoucementClicked.getCity());
        announcementIntent.putExtra("rating", annoucementClicked.getRating());
        announcementIntent.putExtra("description", annoucementClicked.getDescription());
        announcementIntent.putExtra("distance", annoucementClicked.getDistance());
        announcementIntent.putExtra("photo", annoucementClicked.getPhoto());
        announcementIntent.putExtra("duration", annoucementClicked.getDuration());
        announcementIntent.putExtra("price", annoucementClicked.getPrice());
        startActivity(announcementIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_home);

        // initialize
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // item from menu selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        return true;
                }
                return false;
            }
        });

        ButterKnife.bind(this);
        ArrayList<Annoucement> annoucements = new ArrayList<>();
        annoucements.add(new Annoucement("Full Name1","Rabat","2.5 (500)","300DH","2 jrs","900m","Je suis à la recherche d'un plombier...........",R.drawable.photo));
        annoucements.add(new Annoucement("Full Name2","Rabat","2.5 (500)","300DH","2 jrs","900m","Je suis à la recherche d'un plombier...........",R.drawable.photo));
        annoucements.add(new Annoucement("Full Name3","Rabat","2.5 (500)","300DH","2 jrs","900m","Je suis à la recherche d'un plombier...........",R.drawable.photo));
        AnnoucementAdapter annoucementAdapter = new AnnoucementAdapter(getApplicationContext(),R.layout.annoucement_cell_layout,annoucements);
        announcementList.setAdapter(annoucementAdapter);
    }
}