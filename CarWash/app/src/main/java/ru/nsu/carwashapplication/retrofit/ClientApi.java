package ru.nsu.carwashapplication.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ru.nsu.carwashapplication.model.Client;
import ru.nsu.carwashapplication.model.Order;
import ru.nsu.carwashapplication.model.callhttp.loginCallback;
import ru.nsu.carwashapplication.model.callhttp.loginCallsend;

public interface ClientApi {

    @GET("/client/get-all")
    Call<List<Client>> getAllClients();

    @GET("/order/get-all")
    Call<List<Order>> getAllOrders();

    @POST("/client/save")
    Call<Client> save(@Body Client client);

    @POST("/api/auth/signin")
    Call<loginCallback> login(@Body loginCallsend ss);

}