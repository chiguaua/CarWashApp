package ru.nsu.carwashapplication;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import ru.nsu.carwashapplication.model.Offer;
import ru.nsu.carwashapplication.model.Order;

public class OrderBasket extends AppCompatActivity {

    DrawerLayout driverLayout;
    ActionBarDrawerToggle actionBar;
    ListView orderList;
    List<String> offerTitle;
    ArrayAdapter<String> adapter;
    List<String> selectedOffers = new ArrayList<>();
    List<Integer> ids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_basket);

        orderList = findViewById(R.id.orderList);

        offerTitle = new ArrayList<>();
        for (Offer o : CentralPage.allOffersList) {
            if (Order.items_id.contains(o.getId())
                    && !offerTitle.contains(o.getTitle())) {
                offerTitle.add(o.getTitle());
                ids.add(o.getId());
            }
        }

        //R.layout.each_order_layout, R.id.offerName
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, offerTitle);

        orderList.setOnItemClickListener((parent, view, position, id) -> {
            String offer = adapter.getItem(position);
            if (orderList.isItemChecked(position))
                selectedOffers.add(offer);
            else
                selectedOffers.remove(offer);
        });

        orderList.setAdapter(adapter);


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
                Intent intent = new Intent(OrderBasket.this, About_us.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            if (id == R.id.nav_main) {
                Toast.makeText(getApplicationContext(), "Вы выбрали раздел Топаз!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderBasket.this, CentralPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            return true;
        });
    }

    public void remove(View view){
        // получаем и удаляем выделенные элементы
        for(int i=0; i< selectedOffers.size();i++){
            adapter.remove(selectedOffers.get(i));
            Order.items_id.remove(ids.get(i));
        }
        // снимаем все ранее установленные отметки
        orderList.clearChoices();
        // очищаем массив выбраных объектов
        selectedOffers.clear();
        adapter.notifyDataSetChanged();
    }
}