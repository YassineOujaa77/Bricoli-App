package com.example.bricoli.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.bricoli.R;
import com.example.bricoli.adapters.ClientHistoryAdapter;
import com.example.bricoli.adapters.HistoryPostDetailsAdapter;
import com.example.bricoli.models.Annoucement;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HistoryPostDetailsActivity extends AppCompatActivity {

    private ListView listView;
    private HistoryPostDetailsAdapter historyPostDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_post_details);

        // initialize
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set History Selected
        bottomNavigationView.setSelectedItemId(R.id.history);

        // item from menu selected listener
        // item from menu selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),ClientHomeActivity.class));
                        return true;
                    case R.id.current:
                        startActivity(new Intent(getApplicationContext(), PostsActuelActivity.class));
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), ClientHistoryActivity.class));
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                        return true;
                    default:
                        return false;

                }
            }
        });

        listView = (ListView) findViewById(R.id.history_post_detail_listview);
        ArrayList<Annoucement> annoucementsList = new ArrayList<>();
        annoucementsList.add(new Annoucement("full name 1", "4.5 (520)", "2 days", "Lorem ipsum dolor sit amet, consectetur adipiscin g elit, sed do eiusmod tempor incididunt ut labor et dolore magna aliqua. Ut enim ad minim veniam",R.drawable.userphoto, "200 DH"));
        historyPostDetailsAdapter = new HistoryPostDetailsAdapter(this, annoucementsList);
        listView.setAdapter(historyPostDetailsAdapter);
    }
}