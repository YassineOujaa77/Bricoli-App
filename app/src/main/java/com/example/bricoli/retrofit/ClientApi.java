package com.example.bricoli.retrofit;

import com.example.bricoli.models.Client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClientApi {
    @PUT("updateClient/{clientId}")
    Call<Client> updateClient(Client client, @Path("clientid") Long clientId);

}
