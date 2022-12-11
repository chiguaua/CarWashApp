package ru.nsu.carwashapplication;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;
import java.util.List;

import ru.nsu.carwashapplication.adapter.OfferAdapter;
import ru.nsu.carwashapplication.model.Offer;

public class CentralPage extends AppCompatActivity {

    RecyclerView offerRecycler;
    OfferAdapter offerAdapter;

    DrawerLayout driverLayout;
    ActionBarDrawerToggle actionBar;
    static List<Offer> allOffersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central_page);

        List<Offer> offerList = new ArrayList<>();
        offerList.add(new Offer(1, "swgoffer3", "Услуга 1", "1 час", "1000 руб", "#8B7DB1", "#745AC3", "Test"));
        offerList.add(new Offer(2, "swgoffer2", "Услуга 2", "2ч", "750 руб", "#7F9DB5", "#106F92", "Test"));
        offerList.add(new Offer(3, "swgoffer1", "Услуга 3", "1ч", "1000 руб", "#57779C", "#5DB0A5", "Test"));
        offerList.add(new Offer(4, "swgoffer5", "Услуга 4", "2ч", "750 руб", "#9D8B2D", "#E2CA4A", "Test"));
        offerList.add(new Offer(5, "swgoffer4", "Услуга 5", "1ч", "1000 руб", "#42606B", "#63C3EC", "Test"));
        offerList.add(new Offer(6, "swgoffer6", "Услуга 6", "2ч", "750 руб", "#42606B", "#A9D5DF", "Test"));

        allOffersList.addAll(offerList);

        setOfferRecycler(offerList);

        // Обработка нажатия на картинку menu
        findViewById(R.id.imageMenu).setOnClickListener(v -> driverLayout.openDrawer(GravityCompat.START));

        driverLayout = findViewById(R.id.drawer_layout);
        actionBar = new ActionBarDrawerToggle(this, driverLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBar.setDrawerIndicatorEnabled(true);

        driverLayout.addDrawerListener(actionBar);
        actionBar.syncState();

        // Делаю переход через startActivity() -> Возможно сделать как-нибудь изящнее!
        // Еще каждый переход - отельный Fragment, у меня же - отдельная Activity

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_about_us) {
                Toast.makeText(getApplicationContext(), "Вы выбрали раздел про нас!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CentralPage.this, About_us.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            if (id == R.id.nav_main) {
                Toast.makeText(getApplicationContext(), "Вы выбрали раздел Топаз!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CentralPage.this, CentralPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            return true;
        });
    }

    public void openCard(View view) {
        Intent intent = new Intent(this, OrderBasket.class);
        startActivity(intent);
    }

    private void setOfferRecycler(List<Offer> offerList) {
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        offerRecycler = findViewById(R.id.offerRecycler);
        offerRecycler.setLayoutManager(layoutManager);

        offerAdapter = new OfferAdapter(this, offerList);
        offerRecycler.setAdapter(offerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBar.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}