package com.example.newactivity.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newactivity.OfferPage;
import com.example.newactivity.R;
import com.example.newactivity.model.Offer;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {

    Context context;
    List<Offer> offers;

    public OfferAdapter(Context context, List<Offer> offers) {
        this.context = context;
        this.offers = offers;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View offerItems = LayoutInflater.from(context).inflate(R.layout.offer_layout, parent, false);
        return new OfferViewHolder(offerItems);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.offerBg.setBackgroundColor(Color.parseColor(offers.get(position).getColor()));

        int imageId = context.getResources().getIdentifier
                ("ic_" + offers.get(position).getImg(),"drawable", context.getPackageName());
        holder.offerImage.setImageResource(imageId);

        holder.offerTitle.setText(offers.get(position).getTitle());
        holder.offerPrice.setText(offers.get(position).getPrice());
        holder.offerTime.setText(offers.get(position).getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OfferPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) context,
                        new Pair<View, String>(holder.offerImage, "offerImage")
                );

                intent.putExtra("offerBg", Color.parseColor(offers.get(position).getColor()));
                intent.putExtra("offerImage", imageId);
                intent.putExtra("offerTitle", offers.get(position).getTitle());
                intent.putExtra("offerPrice", offers.get(position).getPrice());
                intent.putExtra("offerTime", offers.get(position).getTime());
                intent.putExtra("offerText", offers.get(position).getText());

                context.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public static final class OfferViewHolder extends  RecyclerView.ViewHolder{

        LinearLayout offerBg;
        ImageView offerImage;
        TextView  offerTitle, offerTime, offerPrice;

        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);

            offerBg = itemView.findViewById(R.id.offerBg);
            offerImage = itemView.findViewById(R.id.offerImage);
            offerTitle = itemView.findViewById(R.id.offerTitle);
            offerTime = itemView.findViewById(R.id.offerTime);
            offerPrice = itemView.findViewById(R.id.offerPrice);
        }
    }
}
