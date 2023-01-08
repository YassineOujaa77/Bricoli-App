package com.example.bricoli.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceForWorker {
    private Retrofit retrofit;
    public RetrofitServiceForWorker ()
    {
        initializeRetrofit();
    }

    private void initializeRetrofit()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.1.109:8080/api/vo/worker/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit()
    {
        return retrofit;
    }
}
