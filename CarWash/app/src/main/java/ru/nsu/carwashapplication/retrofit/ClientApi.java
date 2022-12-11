package ru.nsu.carwashapplication.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ru.nsu.carwashapplication.model.Client;
import ru.nsu.carwashapplication.model.callhttp.loginCallback;
import ru.nsu.carwashapplication.model.callhttp.loginCallsend;
import ru.nsu.carwashapplication.model.callhttp.signupSend;

public interface ClientApi {

    @GET("/client/get-all")
    Call<List<Client>> getAllClients();

    @POST("/api/auth/signup")
    Call<Client> save(@Body signupSend ss);

    @POST("/api/auth/signin")
    Call<loginCallback> login(@Body loginCallsend ss);
}