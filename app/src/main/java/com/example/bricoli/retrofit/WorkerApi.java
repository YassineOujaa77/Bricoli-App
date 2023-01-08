package com.example.bricoli.retrofit;

import com.example.bricoli.models.Client;
import com.example.bricoli.models.Worker;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WorkerApi {

    @PUT("updateWorker/{workerId}")
    Call<Worker> updateWorker(@Body Worker worker, @Path("workerId") Long workerId);

    @GET("byId/{workerId}")
    Call<Worker> getWorkerById(@Path("workerId") Long id);


}
