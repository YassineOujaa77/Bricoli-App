package com.example.bricoli.retrofit;

import com.example.bricoli.models.Postulation;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostulationApi {
    @POST("postulation")
    Call<Postulation> addPostulation(@Body Postulation postulation);
}
