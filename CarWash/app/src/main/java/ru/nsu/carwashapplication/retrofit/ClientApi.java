package ru.nsu.carwashapplication.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ru.nsu.carwashapplication.model.Client;

public interface ClientApi {

    @GET("/client/get-all")
    Call<List<Client>> getAllClients();

    @POST("/client/save")
    Call<Client> save(@Body Client client);
}