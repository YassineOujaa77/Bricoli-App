package com.example.bricoli.retrofit;

import com.example.bricoli.models.Postulation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostulationApi {

    @GET("postulation/byClientIdAndState/{clientId}/{state}")
    Call<ArrayList<Postulation>> getPostulationByClientIdAndState(@Path("clientId") Long clientId,@Path("state") String state);
}
