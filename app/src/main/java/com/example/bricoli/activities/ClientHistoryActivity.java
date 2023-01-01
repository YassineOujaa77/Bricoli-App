package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bricoli.R;
import com.example.bricoli.adapters.ClientHistoryAdapter;
import com.example.bricoli.adapters.HomeBidsAdapter;
import com.example.bricoli.models.Annoucement;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;
import com.example.bricoli.retrofit.OfferApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;


import android.os.Bundle;

public class ClientHistoryActivity extends AppCompatActivity {

    private ListView listView;
    private ClientHistoryAdapter clientHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_history);

        listView = (ListView) findViewById(R.id.history_listview);


//        ArrayList<Annoucement> annoucementsList = new ArrayList<>();
//        annoucementsList.add(new Annoucement("full Name1", "Rabat", "2.5 (500)", "2 days", "je suis entrain de chercher un plombier pour réparation d'une ...", R.drawable.userphoto));
//        annoucementsList.add(new Annoucement("full Name2", "Salé", "3 (200)", "10 days", "je suis entrain de chercher un technicien pour réparation d'une ...", R.drawable.userphoto));
//        annoucementsList.add(new Annoucement("full Name3", "Meknes", "4 (670)", "5 days", "je suis à la recherche d'un organisateur de fête ...", R.drawable.userphoto));

//        clientHistoryAdapter = new ClientHistoryAdapter(this, annoucementsList);
//        listView.setAdapter(clientHistoryAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openActivity();
            }
        });

        // initialize
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set History Selected
        bottomNavigationView.setSelectedItemId(R.id.history);

        // item from menu selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
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

    }

    private void loadEmployees() {
        RetrofitService retrofitService = new RetrofitService();
        OfferApi offerApi = retrofitService.getRetrofit().create(OfferApi.class);
        offerApi.getOfferByClientIdAndState(2L, 3L)
                .enqueue(new Callback<Client>() {
                    @Override
                    public void onResponse(Call<Client> call, Response<Client> response) {

                    }

                    @Override
                    public void onFailure(Call<Client> call, Throwable t) {

                    }
                });
    }

    private void populateListView(ArrayList<Offer> offersList) {
        clientHistoryAdapter = new ClientHistoryAdapter(this, offersList);
        listView.setAdapter(clientHistoryAdapter);
    }

    public void openActivity() {
        Intent intent = new Intent(this, HistoryPostDetailsActivity.class);
        startActivity(intent);
    }
}