package com.example.bricoli.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bricoli.R;
import com.example.bricoli.activities.AnnouncementDetailsActivity;
import com.example.bricoli.activities.WorkerHomeActivity;
import com.example.bricoli.models.Offer;

import java.util.ArrayList;

public class OfferAdapter extends ArrayAdapter<Offer> {
    private ArrayList<Offer> offers;
    private Context currentContext;
    public OfferAdapter(Context context, int resource, ArrayList<Offer> offers) {
        super(context, resource, offers);
        this.offers = offers;
        this.currentContext = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.annoucement_cell_layout, parent, false);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.photo);
        //imageView.setBackgroundResource(offers.get(position).getClient().getPhoto());
        imageView.setBackgroundResource(R.drawable.profil);

        TextView fullName = (TextView) convertView.findViewById(R.id.fullName);
        fullName.setText(offers.get(position).getClient().getFullName());

        TextView city = (TextView) convertView.findViewById(R.id.city);
        //city.setText(offers.get(position).getClient().getCity());
        city.setText("city");

        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        rating.setText(offers.get(position).getClient().getSommeRating().toString());


        TextView distance = (TextView) convertView.findViewById(R.id.distance);
        //distance.setText(offers.get(position).getClient().getDistance());
        distance.setText("1Km");

        TextView description = (TextView) convertView.findViewById(R.id.description);
        description.setText(offers.get(position).getDescription());

        Button applyButton = (Button) convertView.findViewById(R.id.bid_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Offer offerClicked = (Offer) offers.get(position);
                Intent announcementIntent = new Intent(currentContext, AnnouncementDetailsActivity.class);
                announcementIntent.putExtra("fullName", offerClicked.getClient().getFullName());
                announcementIntent.putExtra("city", "city");
                announcementIntent.putExtra("rating", offerClicked.getClient().getSommeRating());
                announcementIntent.putExtra("description", offerClicked.getDescription());
                announcementIntent.putExtra("distance", "1km");
                announcementIntent.putExtra("photo", R.drawable.profil);
                currentContext.startActivity(announcementIntent);
            }
        });

        return convertView;

    }
}
