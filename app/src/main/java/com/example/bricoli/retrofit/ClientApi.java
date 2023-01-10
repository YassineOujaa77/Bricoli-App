package com.example.bricoli.retrofit;

import com.example.bricoli.models.Client;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ClientApi {

    @PUT("client/updateClient/{clientId}")
    Call<Client> updateClient(@Body Client client, @Path("clientId") Long clientId);

}
