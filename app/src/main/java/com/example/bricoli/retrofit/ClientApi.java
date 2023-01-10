package com.example.bricoli.retrofit;

import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ClientApi {


    @GET("client/byId/{id}")
    Call<Client> getClient(@Path("id") Long clientId);


    @PUT("client/updateClient/{clientId}")
    Call<Client> updateClient(@Body Client client, @Path("clientId") Long clientId);


    @POST("client/addClient")
    Call<Client> addClient(@Body Client client);
    
    

    @DELETE("client/deleteClient/{clientId}")
    Call <Void> deleteClient(@Path("clientId") Long clientId);

    




}
