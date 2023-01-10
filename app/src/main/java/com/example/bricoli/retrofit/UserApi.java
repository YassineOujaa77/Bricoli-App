package com.example.bricoli.retrofit;

import com.example.bricoli.models.Client;
import com.example.bricoli.models.Worker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi
{
    @GET("client/byPhone/{phone}")
    Call <Client> getClientByPhoneNumber(@Path("phone") String phone);

    @GET("worker/byPhone/{phone}")
    Call <Worker> getWorkerByPhoneNumber(@Path("phone") String phone);

}