package ru.nsu.carwashapplication;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import ru.nsu.carwashapplication.adapter.OfferAdapter;
import ru.nsu.carwashapplication.model.Offer;
import ru.nsu.carwashapplication.model.globalVar;

public class CentralPage extends AppCompatActivity {

    RecyclerView offerRecycler;
    OfferAdapter offerAdapter;

    DrawerLayout driverLayout;
    ActionBarDrawerToggle actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central_page);

        String userMail =  globalVar.getUserMail();;    // Hello World
        TextView textView = findViewById(R.id.textView4);
        textView.setText(userMail);


        List<Offer> offerList = new ArrayList<>();
        offerList.add(new Offer(1, "4x64", "Услуга 1", "1ч", "1000 руб", "#DF8500", "Test"));
        offerList.add(new Offer(2, "4x64", "Услуга 2", "2ч", "750 руб", "#DF8500", "Test"));
        offerList.add(new Offer(3, "4x64", "Услуга 3", "1ч", "1000 руб", "#DF8500", "Test"));
        offerList.add(new Offer(4, "4x64", "Услуга 4", "2ч", "750 руб", "#DF8500", "Test"));
        offerList.add(new Offer(5, "4x64", "Услуга 5", "1ч", "1000 руб", "#DF8500", "Test"));
        offerList.add(new Offer(6, "4x64", "Услуга 6", "2ч", "750 руб", "#DF8500", "Test"));

        setOfferRecycler(offerList);

        // Обработка нажатия на картинку menu
        findViewById(R.id.imageMenu).setOnClickListener(v -> {
            driverLayout.openDrawer(GravityCompat.START);
        });

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
            }
            if (id == R.id.nav_main) {
                Toast.makeText(getApplicationContext(), "Вы выбрали раздел Топаз!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,CentralPage.class));
            }
            if (id == R.id.nav_myOrd) {
                Toast.makeText(getApplicationContext(), "Вы выбрали раздел Топаз!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,OrdList.class));
            }
            return true;
        });
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
        return actionBar.onOptionsItemSelected(item) ||  super.onOptionsItemSelected(item);
    }
}