package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bricoli.R;
import com.example.bricoli.fragments.MapFragmentForWorker;

public class JobDetailsForWorkerActivity extends AppCompatActivity {
    private static final int CALL_PERMISSION_REQUEST_CODE = 1;
    private Button callbutton;
    private String clientnumber;
    private ImageButton imageButton;

    public void openMap(){
        Intent intent=new Intent(this, MapForWorkerActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details_for_worker);
        //initialize the number
        clientnumber="0658601214";
        callbutton = findViewById(R.id.callbutton);

        Fragment fragment= new MapFragmentForWorker();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });

        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+clientnumber));

                if (ActivityCompat.checkSelfPermission(JobDetailsForWorkerActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    askForCallPermission();
                    return;
                }
                startActivity(callIntent);
            }
        });
    }



    public void askForCallPermission() {
        ActivityCompat.requestPermissions(JobDetailsForWorkerActivity.this,
                new String[]{Manifest.permission.CALL_PHONE},
                CALL_PERMISSION_REQUEST_CODE);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + clientnumber));
                startActivity(callIntent);
            } else {
                Toast.makeText(JobDetailsForWorkerActivity.this, "You cannot call without accepting this permission.", Toast.LENGTH_SHORT).show();
            }
        }
    }


}