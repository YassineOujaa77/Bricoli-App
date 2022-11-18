package com.example.bricoli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
public class workerinformations extends AppCompatActivity {


    private ImageButton imageButton;
    public void openMap(){
        Intent intent=new Intent(this, mapactivity.class);
        startActivity(intent);
    }
    public void setratestars(int starsnbr)
    {
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

            TextView nom= (TextView) findViewById(R.id.textView);
            TextView ratenbr= (TextView) findViewById(R.id.rateplusnbr);
            TextView price= (TextView) findViewById(R.id.tv_price);
            TextView time= (TextView) findViewById(R.id.tv_time);

            Fragment fragment=new MapFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
        }
    }
