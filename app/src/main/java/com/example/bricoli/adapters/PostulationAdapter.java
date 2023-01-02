package com.example.bricoli.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bricoli.R;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Postulation;

import java.util.ArrayList;
import java.util.List;

public class PostulationAdapter extends ArrayAdapter<Postulation> {

    private Context aContext ;
    private List<Postulation> postulationsList;

    public PostulationAdapter(@NonNull Context context, @NonNull ArrayList<Postulation> list) {
        super(context, 0, list);
        aContext = context ;
        postulationsList = list;
    }

    @NonNull
    @Override
    public View getView(int position , @Nullable View convertView , @NonNull ViewGroup parent){
        View listItem = convertView ;
        if(listItem == null){
            listItem = LayoutInflater.from(aContext).inflate(R.layout.history_offre_cell,parent,false);
        }

        Postulation currentPostulation = postulationsList.get(position);

        ImageView avatar=(ImageView) listItem.findViewById(R.id.worker_image);
        avatar.setImageResource(R.drawable.userphoto);
        //avatar.setImageResource(currentOffer.getPhoto());

        TextView fullName , sommeRating , numberOfRating, city , duration , description ;


        fullName = (TextView) listItem.findViewById(R.id.nom_complet);
        fullName.setText(currentPostulation.getWorker().getFullName());

        sommeRating = (TextView) listItem.findViewById(R.id.sommeRating_textView);
        sommeRating.setText(""+round((double)currentPostulation.getWorker().getSommeRating()/currentPostulation.getWorker().getNumberOfRating(), 1));

        numberOfRating = (TextView) listItem.findViewById(R.id.numberOfRating_textView);
        numberOfRating.setText(" ("+currentPostulation.getWorker().getNumberOfRating()+") ");

        city = (TextView) listItem.findViewById(R.id.city_textView);
        city.setText("Rabat");

        duration = (TextView) listItem.findViewById(R.id.work_duration_textView);
        duration.setText(""+currentPostulation.getDuration()+" days");

        description=(TextView) listItem.findViewById(R.id.work_description);
        description.setText(currentPostulation.getOffer().getDescription());

        return listItem;

    }
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
