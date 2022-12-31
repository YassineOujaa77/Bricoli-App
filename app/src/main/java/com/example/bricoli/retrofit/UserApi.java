package com.example.bricoli.retrofit;

import com.example.bricoli.models.Client;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi
{
    @GET("byPhone/{phone}")
    Call <Client> getClientByPhoneNumber(@Path("phone") String phone);

}