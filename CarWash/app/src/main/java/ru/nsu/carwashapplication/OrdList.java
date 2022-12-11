package ru.nsu.carwashapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.nsu.carwashapplication.model.Order;
import ru.nsu.carwashapplication.retrofit.ClientApi;
import ru.nsu.carwashapplication.retrofit.RetrofitServiceJson;


public class OrdList extends AppCompatActivity implements View.OnClickListener {

    private Button next, onCentral;
    private TextView currOrd;
    private List<Order> orders;
    private int cursore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.actyvity_order_page);

        next = (Button) findViewById(R.id.nextOrd);
        next.setOnClickListener(this);

        onCentral = (Button) findViewById(R.id.toCentral);
        onCentral.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextOrd:
                if (cursore == 0){
                    getOrd();
                    String userMail =  orders.get(cursore++).toString();;    // Hello World
                    TextView textView = findViewById(R.id.Order);
                    textView.setText(userMail);
                } else {
                    String userMail = orders.get(cursore++).toString();
                    TextView textView = findViewById(R.id.Order);
                    textView.setText(userMail);
                }
                if (cursore == orders.size()){
                    cursore = 0;
                }
                break;
            case R.id.toCentral:
                startActivity(new Intent(this, CentralPage.class));
                break;
        }
    }

    private void getOrd(){
        RetrofitServiceJson rS = new RetrofitServiceJson();
        ClientApi clientApi = rS.getRetrofit().create(ClientApi.class);
        cursore =0;
        clientApi.getAllOrders()
                .enqueue(new Callback<List<Order>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
                        Toast.makeText(OrdList.this, "Work!", Toast.LENGTH_LONG).show();
                        if (response != null) {
                           // orders = response.body();
                        }

                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
                        // String resp = "Вход не удался: ".concat(t.toString());
                        //Toast.makeText(LogInPage.this, resp, Toast.LENGTH_LONG).show();
                        //progressBar.setVisibility(View.INVISIBLE);
                        //Logger.getLogger(LogInPage.class.getName()).log(Level.SEVERE,"Ошибка",t);

                    }
                });


    }

}

