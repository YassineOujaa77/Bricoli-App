package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bricoli.R;
import com.example.bricoli.adapters.ClientHistoryAdapter;
import com.example.bricoli.adapters.HomeBidsAdapter;
import com.example.bricoli.models.Annoucement;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


import android.os.Bundle;

public class ClientHistoryActivity extends AppCompatActivity {

    private ListView listView;
    private ClientHistoryAdapter clientHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_history);

        // initialize
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set Histpry Selected
        bottomNavigationView.setSelectedItemId(R.id.history);

        // item from menu selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.history:
                        return true;

                }


                return false;
            }
        });

        listView = (ListView) findViewById(R.id.history_listview);
        ArrayList<Annoucement> annoucementsList = new ArrayList<>();
        annoucementsList.add(new Annoucement("full Name1", "Rabat", "2.5 (500)", "2 days", "je suis entrain de chercher un plombier pour réparation d'une ...", R.drawable.userphoto));
        annoucementsList.add(new Annoucement("full Name2", "Salé", "3 (200)", "10 days", "je suis entrain de chercher un technicien pour réparation d'une ...", R.drawable.userphoto));
        annoucementsList.add(new Annoucement("full Name3", "Meknes", "4 (670)", "5 days", "je suis à la recherche d'un organisateur de fête ...", R.drawable.userphoto));

        clientHistoryAdapter = new ClientHistoryAdapter(this, annoucementsList);
        listView.setAdapter(clientHistoryAdapter);


    }
}