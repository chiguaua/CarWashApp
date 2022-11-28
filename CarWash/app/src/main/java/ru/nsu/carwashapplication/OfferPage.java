package ru.nsu.carwashapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OfferPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_page);

        ConstraintLayout offerBg = findViewById(R.id.offerPageBg);
        ImageView offerImage = findViewById(R.id.offerPageImage);
        TextView offerTitle = findViewById(R.id.offerPageTitle);
        TextView offerTime = findViewById(R.id.offerPageTime);
        TextView offerPrice = findViewById(R.id.offerPagePrice);
        TextView offerText = findViewById(R.id.offerPageText);

        offerBg.setBackgroundColor(getIntent().getIntExtra("offerBg", 0));
        offerImage.setImageResource(getIntent().getIntExtra("offerImage", 0));
        offerTitle.setText(getIntent().getStringExtra("offerTitle"));
        offerTime.setText(getIntent().getStringExtra("offerTime"));
        offerPrice.setText(getIntent().getStringExtra("offerPrice"));
        offerText.setText(getIntent().getStringExtra("offerText"));

    }
}