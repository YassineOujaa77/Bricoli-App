package com.example.bricoli.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.bricoli.adapters.OfferAdapter;
import com.example.bricoli.enumeration.Category;
import com.example.bricoli.enumeration.OfferState;
import com.example.bricoli.R;
import com.example.bricoli.models.Offer;
import com.example.bricoli.retrofit.OfferApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkerHomeActivity extends AppCompatActivity {

    @BindView(R.id.annoucementList)
    ListView announcementList;
    ArrayList<Offer> offers = new ArrayList<>();

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
                    case R.id.current:
                        startActivity(new Intent(getApplicationContext(),CurrentBidsActivity.class));
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(),HistoryOffreActivity.class));
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
                        return true;
                    default:
                        return false;

                }

            }
        });

        ButterKnife.bind(this);
        callGetOffersByCategroyAndStateApi();
    }

    private void callGetOffersByCategroyAndStateApi(){
        RetrofitService retrofit = new RetrofitService();
        OfferApi offerApi = retrofit.getRetrofit().create(OfferApi.class);
        Call<List<Offer>> offersApi=offerApi.getOffersbyCategoryAndState(getClientCategory(), OfferState.offerstate.toString());
        offersApi.enqueue(new Callback<List<Offer>>() {
            @Override
            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                List<Offer> offerList = response.body();
                if(offerList.size()>0){
                    associateOfferAdapterAndAnnouncementList(offerList);
                }
            }

            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {
                Log.d("client","*********************** Echec");
            }
        });
    }
    private void associateOfferAdapterAndAnnouncementList(List<Offer> offersList){
        for(int i=0;i<offersList.size();i++){
            this.offers.add(offersList.get(i));
        }
        OfferAdapter offerAdapter = new OfferAdapter(WorkerHomeActivity.this,R.layout.annoucement_cell_layout,offers);
        announcementList.setAdapter(offerAdapter);
    }
    private String getClientCategory(){
        return Category.offercategory.toString();
    }
}
