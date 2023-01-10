package com.example.bricoli.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bricoli.R;
import com.example.bricoli.activities.JobDetailsForWorkerActivity;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;
import com.example.bricoli.retrofit.ClientApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.example.bricoli.util.Localisationbyaddress;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapFragmentForWorker extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_for_worker, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MY_MAPFORWORKER);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {


                Offer offer= JobDetailsForWorkerActivity.staticoffre;
                Long myclientid=offer.getClient().getUserId();

                RetrofitService retrofit = new RetrofitService();
                ClientApi myclientapi = retrofit.getRetrofit().create(ClientApi.class);
                Call<Client> client=myclientapi.getClientById(myclientid);
                client.enqueue(new Callback<Client>() {
                    @Override
                    public void onResponse(Call<Client> call, Response<Client> response) {
                        Client saad = response.body();
                        Localisationbyaddress localisationbyaddress=new Localisationbyaddress();
                        String address1 = saad.getAddress();
                        System.out.println("***************************************");
                        System.out.println(address1);
                        localisationbyaddress.setlalon(address1);
                        System.out.println(localisationbyaddress.city);
                        MarkerOptions markerOptions = new MarkerOptions();
                        //markerOptions.position(latLng);
                        LatLng sydney = new LatLng(localisationbyaddress.latitude, localisationbyaddress.longitude);


                        markerOptions.title(sydney.latitude + " KG " + sydney.longitude);
                        //googleMap.clear();


                        markerOptions.position(sydney);
                        googleMap.addMarker(new MarkerOptions().position(sydney).title("Client Position"));


                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));
                        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                            @Override
                            public void onMapClick(@NonNull LatLng latLng) {


                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<Client> call, Throwable t) {

                    }
                });

            }
        });
        return view;
    }


}