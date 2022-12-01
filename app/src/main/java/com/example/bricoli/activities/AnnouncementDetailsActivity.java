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
        TextView description = (TextView) findViewById(R.id.description_details);
        fillAnnouncementFielsFromIntents(fullName, rating, description);



    }
    public void fillAnnouncementFielsFromIntents(TextView fullName,TextView rating, TextView description){
        Bundle extras = getIntent().getExtras();
        fullName.setText(extras.getString("fullName"));
        rating.setText(extras.getString("rating"));
        description.setText(extras.getString("description"));

    }
}