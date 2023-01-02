package com.example.bricoli.retrofit;

import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OfferApi {

    @GET("offer/byClientIdAndState/{clientId}/{state}")
    Call<ArrayList<Offer>> getOfferByClientIdAndState(@Path("clientId") Long clientId, @Path("state") String state);
}
