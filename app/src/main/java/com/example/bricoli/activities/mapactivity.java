package com.example.bricoli.activities;

import static com.example.bricoli.activities.JobDetailsForClientActivity.latitude;
import static com.example.bricoli.activities.JobDetailsForClientActivity.longitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;

import android.content.Intent;
import android.location.LocationManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.bricoli.R;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Postulation;
import com.example.bricoli.models.Worker;
import com.example.bricoli.retrofit.PostulationApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.example.bricoli.util.Localisationbyaddress;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mapactivity extends AppCompatActivity implements OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapactivity);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.thewholemap);
        mapFragment.getMapAsync(this);

    }

    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }


    public void onMapReady(@NonNull GoogleMap googleMap) {



        Intent intent=getIntent();
        Offer offre = (Offer) getIntent().getSerializableExtra("offer");

        RetrofitService retrofit = new RetrofitService();
        PostulationApi postulationapi = retrofit.getRetrofit().create(PostulationApi.class);
        postulationapi.getPostulationByOfferIdAndState(offre.getOfferId(), "ENCOURSDEXECUTION").enqueue(new Callback<List<Postulation>>() {
            @Override
            public void onResponse(Call<List<Postulation>> call, Response<List<Postulation>> response) {
                List<Postulation> list=new ArrayList<>();
                list=response.body();
                Log.d("test","!!!!!!***********************found in map "+list.get(0).getPostulationId());
                Postulation postulation1=list.get(0);
                System.out.println("***********************found postulation********** in map *********");
                Worker workerfrompost=postulation1.getWorker();
                Localisationbyaddress localisationbyaddress=new Localisationbyaddress();
                String address1 = workerfrompost.getAddress();
                System.out.println("***************************************");
                System.out.println(address1);
                localisationbyaddress.setlalon(address1);
                System.out.println(localisationbyaddress.city);
                MarkerOptions markerOptions = new MarkerOptions();

                LatLng sydney = new LatLng(localisationbyaddress.latitude, localisationbyaddress.longitude);

                markerOptions.title(sydney.latitude + " KG " + sydney.longitude);
                //googleMap.clear();


                markerOptions.position(sydney);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Worker Position"));


                googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {


                    }
                });


            }

            @Override
            public void onFailure(Call<List<Postulation>> call, Throwable t) {

            }
        });








    }
}