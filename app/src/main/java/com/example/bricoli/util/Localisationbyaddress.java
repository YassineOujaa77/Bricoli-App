package com.example.bricoli.util;


import android.content.Context;

import android.location.Address;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Geocoder;


public class Localisationbyaddress  {
    public double latitude;
    public double longitude;
    public String city;
    public String addressconv;
    public double[] tabledeloca;

    public void setlalon(String addressconv){
        String parts[] = addressconv.split("/");
        String tocity[] = addressconv.split(",");
        latitude=Double.parseDouble(parts[0]);
        String str[]=parts[1].split(",");
        longitude=Double.parseDouble(str[0]);
        city=tocity[1];

    }
    public void createlocalisation(Context context) throws IOException {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());
        addresses = geocoder.getFromLocation(this.latitude, this.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        city = addresses.get(0).getLocality();
        System.out.println(city);
        System.out.println(this.city);
    }



}
