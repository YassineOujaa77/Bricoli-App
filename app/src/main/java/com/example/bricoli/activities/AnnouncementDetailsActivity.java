package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bricoli.R;

import butterknife.BindView;

public class AnnouncementDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_details);


        TextView fullName = (TextView) findViewById(R.id.fullName_details);
        TextView rating = (TextView) findViewById(R.id.rating_details);
        TextView price = (TextView) findViewById(R.id.price_details);
        TextView duration = (TextView) findViewById(R.id.duration_details);
        TextView description = (TextView) findViewById(R.id.description_details);
        TextView priceProposed = (TextView) findViewById(R.id.price_proposed);
        TextView durationProposed = (TextView) findViewById(R.id.duration_proposed);
        fillAnnouncementFielsFromIntents(fullName, rating, price, duration, description, priceProposed, durationProposed);



    }
    public void fillAnnouncementFielsFromIntents(TextView fullName,TextView rating,TextView price,TextView duration,TextView description,TextView priceProposed,TextView durationProposed){
        Bundle extras = getIntent().getExtras();
        fullName.setText(extras.getString("fullName"));
        rating.setText(extras.getString("rating"));
        price.setText(extras.getString("price"));
        duration.setText(extras.getString("duration"));
        description.setText(extras.getString("description"));
        priceProposed.setText(extras.getString("price"));
        durationProposed.setText(extras.getString("duration"));
    }
}