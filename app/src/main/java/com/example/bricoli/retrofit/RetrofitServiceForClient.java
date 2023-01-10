package com.example.bricoli.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceForClient {
    private Retrofit retrofit;
    public RetrofitServiceForClient ()
    {
        initializeRetrofit();
    }

    private void initializeRetrofit()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.1.109:8080/api/vo/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit()
    {
        return retrofit;
    }
}
