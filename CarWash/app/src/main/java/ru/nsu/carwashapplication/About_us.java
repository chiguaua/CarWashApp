package ru.nsu.carwashapplication;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class About_us extends AppCompatActivity {

    DrawerLayout driverLayout;
    ActionBarDrawerToggle actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Обработка анимации Shimmer
        Shimmer shimText = new Shimmer();
        ShimmerTextView text = findViewById(R.id.shimmer);
        shimText.start(text);

        // Обработка нажатия на картинку menu
        findViewById(R.id.imageMenuAboutUs).setOnClickListener(v -> driverLayout.openDrawer(GravityCompat.START));

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
                Intent intent = new Intent(About_us.this, About_us.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            if (id == R.id.nav_main) {
                Toast.makeText(getApplicationContext(), "Вы выбрали раздел Топаз!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(About_us.this, CentralPage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            return true;
        });
    }
}