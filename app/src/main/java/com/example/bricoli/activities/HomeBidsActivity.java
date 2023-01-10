package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bricoli.R;
import com.example.bricoli.adapters.HomeBidsAdapter;
import com.example.bricoli.models.Annoucement;
import com.example.bricoli.models.Postulation;
import com.example.bricoli.retrofit.PostulationApi;
import com.example.bricoli.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

public class HomeBidsActivity extends AppCompatActivity {

    private ListView listView;
    private HomeBidsAdapter homeBidsAdapter;
    ArrayList<Postulation> postulationsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_bids);

        listView = (ListView) findViewById(R.id.list_view_for_home_bids);



        /*
        annoucementsList.add(new Annoucement("Full name" ,"Rabat","4.5 (250)","250","2 jrs","900 m",R.drawable.userphoto));
        annoucementsList.add(new Annoucement("Full name" ,"Rabat","4.5 (250)","250","2 jrs","900 m",R.drawable.userphoto));
        annoucementsList.add(new Annoucement("Full name" ,"Rabat","4.5 (250)","250","2 jrs","900 m",R.drawable.userphoto));
        annoucementsList.add(new Annoucement("Full name" ,"Rabat","4.5 (250)","250","2 jrs","900 m",R.drawable.userphoto));
        annoucementsList.add(new Annoucement("Full name" ,"Rabat","4.5 (250)","250","2 jrs","900 m",R.drawable.userphoto));

        */


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),WorkerProfilForClientActivity.class));
            }
        });


        RetrofitService retrofitService = new RetrofitService();
        PostulationApi postulationApi = retrofitService.getRetrofit().create(PostulationApi.class);
        Intent intentHomeBids = getIntent();
        Long offerId = intentHomeBids.getExtras().getLong("idOfferHomeBids");
        Call<List<Postulation>> call= postulationApi.getPostulationByOfferId(4);
        call.enqueue(new Callback<List<Postulation>>() {
            @Override
            public void onResponse(Call<List<Postulation>> call, Response<List<Postulation>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(HomeBidsActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }

                List<Postulation> postulations = response.body();

                for (Postulation postulation : postulations) {
                    postulationsList.add(new Postulation(postulation.getPrice()
                                                    ,postulation.getDuration()
                                                    ,postulation.getState()
                                                    ,postulation.getCreatedAt()
                                                    ,postulation.getWorker()
                                                    ,postulation.getOffer()));
                }

                homeBidsAdapter = new HomeBidsAdapter(HomeBidsActivity.this,postulationsList);
                listView.setAdapter(homeBidsAdapter);
                Toast.makeText(HomeBidsActivity.this, postulationsList.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Postulation>> call, Throwable t) {
                Toast.makeText(HomeBidsActivity.this, "Faild to get postulation try again !", Toast.LENGTH_SHORT).show();
            }
        });




        



    }
}