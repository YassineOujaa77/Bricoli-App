package com.example.bricoli.fragments;

import static com.example.bricoli.activities.JobDetailsForClientActivity.latitude;
import static com.example.bricoli.activities.JobDetailsForClientActivity.longitude;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;


import com.example.bricoli.R;
import com.example.bricoli.activities.JobDetailsForClientActivity;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Postulation;
import com.example.bricoli.models.Worker;
import com.example.bricoli.retrofit.PostulationApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.example.bricoli.util.Localisationbyaddress;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
//here the end
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.location.LocationServices;

import android.Manifest;
import android.content.pm.PackageManager;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        // Inflate the layout for this fragment

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MY_MAP);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {


                Offer offre = JobDetailsForClientActivity.staticoffer;

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
                        //
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
        });
        return view;
    }
}