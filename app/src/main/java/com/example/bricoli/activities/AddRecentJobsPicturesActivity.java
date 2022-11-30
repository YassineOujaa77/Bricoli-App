package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bricoli.R;

import java.util.ArrayList;
import java.util.List;

public class AddRecentJobsPicturesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recent_jobs_pictures);

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        Button addPicture = findViewById(R.id.Add_picture_button);

        List<SlideModel> slideModels =new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.projet1,"", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.projet2,"", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels);

        // When user click on Add picture button , he should be able to import images
        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
    }
}