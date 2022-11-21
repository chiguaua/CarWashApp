package ru.nsu.carwashapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CentralPage extends AppCompatActivity {

    RecyclerView offerRecycler;
    ru.nsu.carwashapplication.adapter.OfferAdapter offerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central_page);

        List<ru.nsu.carwashapplication.model.Offer> offerList = new ArrayList<>();
        offerList.add(new ru.nsu.carwashapplication.model.Offer(1, "4x64", "Услуга 1", "1ч", "1000 руб", "#DF8500", "Test"));
        offerList.add(new ru.nsu.carwashapplication.model.Offer(2, "4x64", "Услуга 2", "2ч", "750 руб", "#DF8500", "Test"));
        offerList.add(new ru.nsu.carwashapplication.model.Offer(3, "4x64", "Услуга 3", "1ч", "1000 руб", "#DF8500", "Test"));
        offerList.add(new ru.nsu.carwashapplication.model.Offer(4, "4x64", "Услуга 4", "2ч", "750 руб", "#DF8500", "Test"));
        offerList.add(new ru.nsu.carwashapplication.model.Offer(5, "4x64", "Услуга 5", "1ч", "1000 руб", "#DF8500", "Test"));
        offerList.add(new ru.nsu.carwashapplication.model.Offer(6, "4x64", "Услуга 6", "2ч", "750 руб", "#DF8500", "Test"));

        setOfferRecycler(offerList);
    }

    private void setOfferRecycler(List<ru.nsu.carwashapplication.model.Offer> offerList) {
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        offerRecycler = findViewById(R.id.offerRecycler);
        offerRecycler.setLayoutManager(layoutManager);

        offerAdapter = new ru.nsu.carwashapplication.adapter.OfferAdapter(this, offerList);
        offerRecycler.setAdapter(offerAdapter);
    }
}