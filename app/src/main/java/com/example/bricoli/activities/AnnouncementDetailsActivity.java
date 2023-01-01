package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bricoli.R;
import com.example.bricoli.enumeration.PostulationState;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Postulation;
import com.example.bricoli.models.Worker;

import java.util.Date;

import butterknife.BindView;

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
        applyButton.setOnClickListener(OnApplyWorkerClicked());
    }
    public void fillAnnouncementFielsFromIntents(TextView fullName,TextView rating, TextView description){
        Intent  intent= getIntent();
        this.offer = (Offer) intent.getSerializableExtra("offer");
        fullName.setText(offer.getClient().getFullName());
        rating.setText(offer.getClient().getSommeRating().toString());
        description.setText(offer.getDescription());

    }
    private View.OnClickListener OnApplyWorkerClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText priceProposed = (EditText)findViewById(R.id.price_proposed);
                EditText durationProposed = (EditText)findViewById(R.id.duration_proposed);
                try {
                    if(!priceProposed.equals("") && !durationProposed.equals("")){
                        float price = Float.parseFloat(priceProposed.getText().toString());
                        int duration = Integer.parseInt(durationProposed.getText().toString());
                        //prend l worker from login
                        Worker worker = new Worker(1L,"cin","pass","adress",10L,1,"ggg","fullName","ggggg","666666");
                        Postulation postulation = new Postulation(price,duration, PostulationState.WAITING.toString(),new Date(),worker,offer);
                        System.out.println(postulation.toString());
                    }
                }
                catch(Exception e){

                }
            }
        };
    }
}