package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.bricoli.R;
import com.example.bricoli.adapters.PostulationAdapter;
import com.example.bricoli.models.Postulation;
import com.example.bricoli.retrofit.PostulationApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientHistoryActivity extends AppCompatActivity {

    private ListView listView;
    private PostulationAdapter postulationAdapter;
    ArrayList<Postulation> postulations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_history);

        listView = (ListView) findViewById(R.id.history_listview);
        loadPostulations();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
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

    private void loadPostulations() {
        RetrofitService retrofitService2 = new RetrofitService();
        PostulationApi postulationApi = retrofitService2.getRetrofit().create(PostulationApi.class);
        Call<List<Postulation>> postulation = postulationApi.getPostulationByClientIdAndState(4L, "0");
        postulation.enqueue(new Callback<List<Postulation>>() {
            @Override
            public void onResponse(Call<List<Postulation>> call, Response<List<Postulation>> response) {
                List<Postulation> listPostulation = response.body();
                if (listPostulation.size() > 0 ){
                    populateListView_postulation(listPostulation);
                }
                else {
                    Toast.makeText(ClientHistoryActivity.this, getResources().getText(R.string.no_postulation_found), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Postulation>> call, Throwable t) {
                Toast.makeText(ClientHistoryActivity.this, getResources().getText(R.string.toast_client_home_fail_add_offer), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListView_postulation(@NonNull List<Postulation> postulationsList) {
        for(int i=0;i<postulationsList.size();i++){
            this.postulations.add(postulationsList.get(i));
        }
        postulationAdapter = new PostulationAdapter(this, postulations);
        listView.setAdapter(postulationAdapter);
    }
}