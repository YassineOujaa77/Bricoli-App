package com.example.bricoli.retrofit;

import com.example.bricoli.models.Postulation;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostulationApi {

    @GET("postulation/byClientIdAndState/{clientId}/{state}")
    Call<ArrayList<Postulation>> getPostulationByClientIdAndState(@Path("clientId") Long clientId,@Path("state") String state);
    
    @POST("postulation")
    Call<Postulation> addPostulation(@Body Postulation postulation);

    @GET("listByWorkerId/{workerId}")
    Call<List<Postulation>> getPostulationsByWorkerId(@Path("workerId") Long workerId);

    @GET("byWorkerIdAndState/{workerId}/{state}")
    Call<List<Postulation>> getPostulationByWorkerIdAndState(@Path("workerId") Long workerId, @Path("state") String state);


}
