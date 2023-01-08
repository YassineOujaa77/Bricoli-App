package com.example.bricoli.retrofit;

import com.example.bricoli.models.Offer;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OfferApi {

    @POST("offer")
    Call<Offer> addOffer(@Body Offer offer);
    
    @GET("offer/byCategoryAndStateNotAlreadyApplied/{category}/{state}/{workerId}")
    Call<List<Offer>> getOfferByCategoryAndStateNotAlreadyApplied(@Path("category") String category,@Path("state") String state, @Path("workerId") Long workerId);


    @GET("getOfferByClientId/{clientId}")
    Call<List<Offer>> getOfferByClientId(@Path("clientId") Long clientId);
}
