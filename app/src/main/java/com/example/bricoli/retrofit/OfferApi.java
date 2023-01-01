package com.example.bricoli.retrofit;

import com.example.bricoli.models.Client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OfferApi {

    @GET("byClientIdAndState/{clientId}/{state}")
    Call<Client> getOfferByClientIdAndState(@Path("clientID") Long clientId, @Path("state") Long state);
}
