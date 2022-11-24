package com.example.bricoli.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bricoli.models.Annoucement;
import com.example.bricoli.R;

import java.util.ArrayList;

public class AnnoucementAdapter extends ArrayAdapter<Annoucement> {
    private ArrayList<Annoucement> annoucements;
    public AnnoucementAdapter(Context context, int resource, ArrayList<Annoucement> annoucements) {
        super(context, resource, annoucements);
        this.annoucements = annoucements;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.annoucement_cell_layout, parent, false);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.photo);
        imageView.setBackgroundResource(annoucements.get(position).getPhoto());

        TextView fullName = (TextView) convertView.findViewById(R.id.fullName);
        fullName.setText(annoucements.get(position).getFullName());

        TextView city = (TextView) convertView.findViewById(R.id.city);
        city.setText(annoucements.get(position).getCity());

        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        rating.setText(annoucements.get(position).getRating());

        TextView price = (TextView) convertView.findViewById(R.id.price);
        price.setText(annoucements.get(position).getPrice());

        TextView duration = (TextView) convertView.findViewById(R.id.duration);
        duration.setText(annoucements.get(position).getDuration());

        TextView distance = (TextView) convertView.findViewById(R.id.distance);
        distance.setText(annoucements.get(position).getDistance());

        TextView description = (TextView) convertView.findViewById(R.id.description);
        description.setText(annoucements.get(position).getDescription());

        return convertView;

    }
}