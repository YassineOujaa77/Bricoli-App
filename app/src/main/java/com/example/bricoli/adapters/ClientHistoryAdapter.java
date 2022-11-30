package com.example.bricoli.adapters;

import android.app.LauncherActivity;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.bricoli.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


import com.example.bricoli.models.Annoucement;


public class ClientHistoryAdapter extends ArrayAdapter<Annoucement> {

    private Context aContext ;
    private List<Annoucement> annoucementsList = new ArrayList<>();

    public ClientHistoryAdapter(@NonNull Context context, @NonNull ArrayList<Annoucement> List) {

        super(context, 0, List);
        aContext = context ;
        annoucementsList = List;
    }

    @NonNull
    @Override
    public View getView(int position , @Nullable View convertView , @NonNull ViewGroup parent){
        View listItem = convertView ;
        if(listItem == null){
            listItem = LayoutInflater.from(aContext).inflate(R.layout.history_offre_cell,parent,false);
        }

        Annoucement currentAnnoucement = annoucementsList.get(position);

        ImageView avatar=(ImageView) listItem.findViewById(R.id.worker_image);
        avatar.setImageResource(currentAnnoucement.getPhoto());

        TextView fullname , rate , city , duration , description ;

        fullname = (TextView) listItem.findViewById(R.id.nom_complet);
        fullname.setText(currentAnnoucement.getFullName());

        rate = (TextView) listItem.findViewById(R.id.rate_textView);
        rate.setText(currentAnnoucement.getRating());

        city = (TextView) listItem.findViewById(R.id.city_textView);
        city.setText(currentAnnoucement.getCity());

        duration = (TextView) listItem.findViewById(R.id.work_duration_textView);
        duration.setText(currentAnnoucement.getDuration());

        description=(TextView) listItem.findViewById(R.id.work_description);
        description.setText(currentAnnoucement.getDescription());

        return listItem;
    }

}
