package com.example.bricoli.retrofit;

import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OfferApi {
    @GET("offer/byCategoryAndStateNotAlreadyApplied/{category}/{state}/{workerId}")
    Call<List<Offer>> getOfferByCategoryAndStateNotAlreadyApplied(@Path("category") String category,@Path("state") String state, @Path("workerId") Long workerId);
}
