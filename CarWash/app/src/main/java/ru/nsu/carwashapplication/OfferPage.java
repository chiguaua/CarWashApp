package ru.nsu.carwashapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ru.nsu.carwashapplication.model.Order;

public class OfferPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_page);

        ConstraintLayout offerBg = findViewById(R.id.offerPageBg);
        Button offerBtnColor = findViewById(R.id.offerButton);
        ImageView offerImage = findViewById(R.id.offerPageImage);
        TextView offerTitle = findViewById(R.id.offerPageTitle);
        TextView offerTime = findViewById(R.id.offerPageTime);
        TextView offerPrice = findViewById(R.id.offerPagePrice);
        TextView offerText = findViewById(R.id.offerPageText);

        offerBg.setBackgroundColor(getIntent().getIntExtra("offerBg", 0));
        offerBtnColor.setBackgroundColor(getIntent().getIntExtra("offerBtnColor", 0));
        offerImage.setImageResource(getIntent().getIntExtra("offerImage", 0));
        offerTitle.setText(getIntent().getStringExtra("offerTitle"));
        offerTime.setText(getIntent().getStringExtra("offerTime"));
        offerPrice.setText(getIntent().getStringExtra("offerPrice"));
        offerText.setText(getIntent().getStringExtra("offerText"));
    }

    public void addToCard(View view){
        int item_id = getIntent().getIntExtra("offerId", 0);
        Order.items_id.add(item_id);
        Toast.makeText(this, "Услуга выбрана!", Toast.LENGTH_SHORT).show();
    }
}