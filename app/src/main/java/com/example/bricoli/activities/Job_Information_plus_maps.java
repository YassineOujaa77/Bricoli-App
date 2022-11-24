package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bricoli.R;
import com.example.bricoli.fragments.MapFragment;

public class Job_Information_plus_maps extends AppCompatActivity {


    private ImageButton imageButton;
    private CardView profilecard;
    private Button finishbutton;
    public void openMap(){
        Intent intent=new Intent(this, mapactivity.class);
        startActivity(intent);
    }
    public void openprofile(){
        // on merge should changed by the profile class
        Intent intent=new Intent(this, Job_Information_plus_maps.class);
        startActivity(intent);
    }
    public void opennextbyfinish(){
        // on merge should changed by the profile class
        Intent intent=new Intent(this, Job_Information_plus_maps.class);
        startActivity(intent);
    }



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_workerinformations);

            imageButton = (ImageButton) findViewById(R.id.imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openMap();
                }
            });

            profilecard = (CardView) findViewById(R.id.profilecardc);
            profilecard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openprofile();
                }
            });
            finishbutton = (Button) findViewById(R.id.finishbutton);
            finishbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    opennextbyfinish();
                }
            });

            TextView nom= (TextView) findViewById(R.id.textView);
            nom.setText("Nom Complet");
            TextView ratenbr= (TextView) findViewById(R.id.rateplusnbr);
            ratenbr.setText("4.5 "+ "(520)");
            TextView price= (TextView) findViewById(R.id.tv_price);
            price.setText("200 DH");
            TextView time= (TextView) findViewById(R.id.tv_time);
            time.setText("2 Jrs");

            Fragment fragment=new MapFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
        }
    }
