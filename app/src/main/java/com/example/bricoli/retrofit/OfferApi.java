package com.example.bricoli.retrofit;

import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OfferApi {
    @GET("getOfferByCategoryAndState/{category}/{state}")
    Call<List<Offer>> getClientByPhoneNumber(@Path("category") String category,@Path("state") String state);
}
