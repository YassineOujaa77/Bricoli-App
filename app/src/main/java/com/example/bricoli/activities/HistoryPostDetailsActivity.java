package com.example.bricoli.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bricoli.R;
import com.example.bricoli.adapters.ClientHistoryAdapter;
import com.example.bricoli.adapters.HistoryPostDetailsAdapter;
import com.example.bricoli.models.Annoucement;
import com.example.bricoli.models.Postulation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HistoryPostDetailsActivity extends AppCompatActivity {

    private Postulation postulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_post_details);

        ImageView avatar = (ImageView) findViewById(R.id.worker_image);
        TextView fullName = (TextView) findViewById(R.id.nom_complet);
        TextView sommeRating = (TextView) findViewById(R.id.sommeRating_textView);
        TextView numberOfRating = (TextView) findViewById(R.id.numberOfRating_textView);
        TextView price1 = (TextView) findViewById(R.id.price_textView);
        TextView price2 = (TextView) findViewById(R.id.price_textView2);
        TextView duration1 = (TextView) findViewById(R.id.work_duration_textView);
        TextView duration2 = (TextView) findViewById(R.id.work_duration_textView2);
        TextView description = (TextView) findViewById(R.id.work_description);
        fillPostulationFieldsFromIntents(avatar, fullName, sommeRating, numberOfRating, price1, price2, duration1, duration2, description);

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

    }

    public void fillPostulationFieldsFromIntents(ImageView avatar, TextView fullName, TextView sommeRating, TextView numberOfRating, TextView price1, TextView price2, TextView duration1, TextView duration2, TextView description){
        Intent  intent= getIntent();
        this.postulation = (Postulation) intent.getSerializableExtra("postulation");
        avatar.setImageResource(R.drawable.userphoto);
        fullName.setText(postulation.getWorker().getFullName());
        sommeRating.setText(""+round((double)postulation.getWorker().getSommeRating()/postulation.getWorker().getNumberOfRating(), 1));
        numberOfRating.setText(" ("+postulation.getWorker().getNumberOfRating()+") ");
        price1.setText(""+postulation.getPrice()+" DH");
        price2.setText(""+postulation.getPrice()+" DH");
        duration1.setText(""+postulation.getDuration()+" days");
        duration2.setText(""+postulation.getDuration()+" days");
        description.setText(postulation.getOffer().getDescription());

    }
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}