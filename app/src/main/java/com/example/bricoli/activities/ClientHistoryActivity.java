package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bricoli.R;
import com.example.bricoli.adapters.ClientHistoryAdapter;
import com.example.bricoli.adapters.PostulationAdapter;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Postulation;
import com.example.bricoli.retrofit.OfferApi;
import com.example.bricoli.retrofit.PostulationApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientHistoryActivity extends AppCompatActivity {

    private ListView listView;
    private ClientHistoryAdapter clientHistoryAdapter;
    private PostulationAdapter postulationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_history);

        listView = (ListView) findViewById(R.id.history_listview);
        //loadOffers();
        loadPostulations();

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



   /* private void loadOffers() {
        RetrofitService retrofitService = new RetrofitService();
        OfferApi offerApi = retrofitService.getRetrofit().create(OfferApi.class);
        Call<ArrayList<Offer>> offer = offerApi.getOfferByClientIdAndState(2L, "3");
        offer.enqueue(new Callback<ArrayList<Offer>>() {
            @Override
            public void onResponse(Call<ArrayList<Offer>> call, Response<ArrayList<Offer>> response) {
                populateListView_offer(response.body());
                if (response.isSuccessful()) {

                    ArrayList<Offer> list_elements = response.body();
                    //Long offerId = list_elements.get(0).getText();
                    for (Offer item : list_elements) {
                        String offerId = item.getText(); // print every text item in list
                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Offer>> call, Throwable t) {
                Toast.makeText(ClientHistoryActivity.this, "Failed to load offers", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    private void loadPostulations() {
        RetrofitService retrofitService2 = new RetrofitService();
        PostulationApi postulationApi = retrofitService2.getRetrofit().create(PostulationApi.class);
        Call<ArrayList<Postulation>> postulation = postulationApi.getPostulationByClientIdAndState(4L, "0");
        postulation.enqueue(new Callback<ArrayList<Postulation>>() {
            @Override
            public void onResponse(Call<ArrayList<Postulation>> call, Response<ArrayList<Postulation>> response) {
                Toast.makeText(ClientHistoryActivity.this, "done", Toast.LENGTH_SHORT).show();
                populateListView_postulation(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Postulation>> call, Throwable t) {
                Toast.makeText(ClientHistoryActivity.this, "Failed to load postulations", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void populateListView_postulation(ArrayList<Postulation> postulationsList) {
        postulationAdapter = new PostulationAdapter(this, postulationsList);
        listView.setAdapter(postulationAdapter);
    }

   /* private void populateListView_offer(ArrayList<Offer> offersList) {
        clientHistoryAdapter = new ClientHistoryAdapter(this, offersList);
        listView.setAdapter(clientHistoryAdapter);
    }*/


    public void openActivity() {
        Intent intent = new Intent(this, HistoryPostDetailsActivity.class);
        startActivity(intent);
    }
}