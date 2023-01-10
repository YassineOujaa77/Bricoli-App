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
import com.example.bricoli.R;
import com.example.bricoli.activities.AnnouncementDetailsActivity;
import com.example.bricoli.models.Offer;

import java.text.SimpleDateFormat;
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
        //distance.setText(offers.get(position).getClient().getDistance());
        String[] address = offers.get(position).getClient().getAddress().split(",",2);
        if(address.length>=1){
            city.setText(address[1]);
        }

        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        Long ratingValue = offers.get(position).getClient().getSommeRating() / offers.get(position).getClient().getNumberOfRating();
        rating.setText(ratingValue +"(" +offers.get(position).getClient().getNumberOfRating().toString()+")");

        TextView distance = (TextView) convertView.findViewById(R.id.distance);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        distance.setText(simpleDateFormat.format(offers.get(position).getCreatedAt()).replace('-','/'));

        TextView description = (TextView) convertView.findViewById(R.id.description);
        description.setText(offers.get(position).getDescription());

        Button applyButton = (Button) convertView.findViewById(R.id.bid_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Offer offerClicked = (Offer) offers.get(position);
                Intent announcementIntent = new Intent(currentContext, AnnouncementDetailsActivity.class);
                announcementIntent.putExtra("offer", offerClicked);
                currentContext.startActivity(announcementIntent);
            }
        });

        return convertView;

    }
}
