package com.example.bricoli.adapters;

import android.app.LauncherActivity;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.bricoli.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


import com.example.bricoli.activities.HistoryPostDetailsActivity;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Postulation;


public class ClientHistoryAdapter extends ArrayAdapter<Offer> {

    private Context aContext ;
    private List<Offer> offersList;
    //private List<Offer> offersList = new ArrayList<>();
    private List<Postulation> postulationsList;

    public ClientHistoryAdapter(@NonNull Context context, @NonNull ArrayList<Offer> List) {

        super(context, 0, List);
        aContext = context ;
        offersList = List;
    }

    @NonNull
    @Override
    public View getView(int position , @Nullable View convertView , @NonNull ViewGroup parent){
        View listItem = convertView ;
        if(listItem == null){
            listItem = LayoutInflater.from(aContext).inflate(R.layout.history_offre_cell,parent,false);
        }



        Offer currentOffer = offersList.get(position);
        Postulation currentPostulation = postulationsList.get(position);

        ImageView avatar=(ImageView) listItem.findViewById(R.id.worker_image);
        avatar.setImageResource(R.drawable.userphoto);
        //avatar.setImageResource(currentOffer.getPhoto());

        TextView fullname , rate , city , duration , description ;

        fullname = (TextView) listItem.findViewById(R.id.nom_complet);
        fullname.setText(currentPostulation.getWorker().getFullName());

        rate = (TextView) listItem.findViewById(R.id.rate_textView);
        rate.setText(currentOffer.getDescription());

        city = (TextView) listItem.findViewById(R.id.city_textView);
        city.setText("Rabat");

        duration = (TextView) listItem.findViewById(R.id.work_duration_textView);
        duration.setText(currentPostulation.getDuration());

        description=(TextView) listItem.findViewById(R.id.work_description);
        description.setText(currentOffer.getDescription());


        return listItem;



    }



}
