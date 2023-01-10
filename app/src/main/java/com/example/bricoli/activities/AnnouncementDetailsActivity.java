package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bricoli.R;
import com.example.bricoli.enumeration.OfferState;
import com.example.bricoli.enumeration.PostulationState;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Postulation;
import com.example.bricoli.models.Worker;
import com.example.bricoli.retrofit.OfferApi;
import com.example.bricoli.retrofit.PostulationApi;
import com.example.bricoli.retrofit.RetrofitService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementDetailsActivity extends AppCompatActivity {
    private Offer offer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_details);
        TextView fullName = (TextView) findViewById(R.id.fullName_details);
        TextView rating = (TextView) findViewById(R.id.rating_details);
        TextView description = (TextView) findViewById(R.id.description_details);
        fillAnnouncementFielsFromIntents(fullName, rating, description);
        Button applyButton = (Button) findViewById(R.id.worker_apply);
        applyButton.setOnClickListener(onApplyWorkerClicked());
    }
    public void fillAnnouncementFielsFromIntents(TextView fullName,TextView rating, TextView description){
        Intent  intent= getIntent();
        this.offer = (Offer) intent.getSerializableExtra("offer");
        fullName.setText(offer.getClient().getFullName());
        Long ratingValue = offer.getClient().getSommeRating() / offer.getClient().getNumberOfRating();
        rating.setText(ratingValue + "("+offer.getClient().getNumberOfRating().toString()+")");
        description.setText(offer.getDescription());

    }
    private View.OnClickListener onApplyWorkerClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText priceProposed = (EditText)findViewById(R.id.price_proposed);
                EditText durationProposed = (EditText)findViewById(R.id.duration_proposed);
                try {
                    if(!priceProposed.getText().toString().equals("") && !durationProposed.getText().toString().equals("")){
                        float price = Float.parseFloat(priceProposed.getText().toString());
                        int duration = Integer.parseInt(durationProposed.getText().toString());
                        //prend l worker from login
                        Worker worker = new Worker(1L,"cin","pass","adress",10L,1,"ggg","fullName","ggggg","666666");
                        offer.setCreatedAt(null);
                        Postulation postulationToAdd = new Postulation(price,duration, PostulationState.WAITING.toString(),null,worker,offer);
                        callAddPostulationApi(postulationToAdd);
                        if(offer.getState().equals(OfferState.EN_ATTENTE.toString())){
                            callUpdateOfferApi(offer);
                        }
                        Toast.makeText(AnnouncementDetailsActivity.this,getString(R.string.duration_price_proposed_not_inserted),Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AnnouncementDetailsActivity.this,WorkerHomeActivity.class));

                    }
                    else{
                        Toast.makeText(getApplicationContext(),getString(R.string.duration_price_proposed_not_inserted),Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(),getString(R.string.duration_price_proposed_not_valid),Toast.LENGTH_SHORT).show();

                }
            }
        };
    }

    private void callAddPostulationApi(Postulation postulationToAdd){
        RetrofitService retrofit = new RetrofitService();
        PostulationApi postulationApi = retrofit.getRetrofit().create(PostulationApi.class);
        Call<Postulation> call=postulationApi.addPostulation(postulationToAdd);
        call.enqueue(new Callback<Postulation>() {
            @Override
            public void onResponse(Call<Postulation> call, Response<Postulation> response) {
            }

            @Override
            public void onFailure(Call<Postulation> call, Throwable t) {
                Log.d("postulation","*********************** Echec");
            }
        });
    }
    private void callUpdateOfferApi(Offer offer){
        offer.setState(OfferState.EN_COURS_NEGOCIATION.toString());
        RetrofitService retrofit = new RetrofitService();
        OfferApi offerApi = retrofit.getRetrofit().create(OfferApi.class);
        Call<Offer> call=offerApi.updateOffer(offer.getOfferId(),offer);
        call.enqueue(new Callback<Offer>() {
            @Override
            public void onResponse(Call<Offer> call, Response<Offer> response) {
                Offer offer1 = response.body();
                System.out.println(offer1.toString());
            }

            @Override
            public void onFailure(Call<Offer> call, Throwable t) {
                Log.d("offer update","*********************** Echec");
            }
        });
    }
}