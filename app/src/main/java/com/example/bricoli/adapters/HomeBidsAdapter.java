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

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bricoli.R;
import com.example.bricoli.activities.JobDetailsForClientActivity;
import com.example.bricoli.models.Annoucement;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeBidsAdapter extends ArrayAdapter<Annoucement> {

    private Context aContext ;
    private List<Annoucement> annoucementsList = new ArrayList<>();

    public HomeBidsAdapter(@NotNull Context context , ArrayList<Annoucement> list){
        super(context,0,list);
        aContext = context ;
        annoucementsList = list ;

    }

    @NotNull
    @Override
    public View getView(int position , @Nullable View convertView , @NonNull ViewGroup parent){
        View listItem = convertView ;
        if(listItem == null){
            listItem = LayoutInflater.from(aContext).inflate(R.layout.list_item_for_home_bids,parent,false);
        }

        Annoucement currentAnnoucement = annoucementsList.get(position);

        ImageView avatar = (ImageView) listItem.findViewById(R.id.worker_image);
        avatar.setImageResource(currentAnnoucement.getPhoto());

        TextView fullname , rate , city , price , duration , distance ;

        fullname = (TextView) listItem.findViewById(R.id.nom_complet);
        fullname.setText(currentAnnoucement.getFullName());

        rate = (TextView) listItem.findViewById(R.id.rate_textView);
        rate.setText(currentAnnoucement.getRating());

        city = (TextView) listItem.findViewById(R.id.city_textView);
        city.setText(currentAnnoucement.getCity());

        price = (TextView) listItem.findViewById(R.id.price_textView);
        price.setText(currentAnnoucement.getPrice());

        duration = (TextView) listItem.findViewById(R.id.days_textView);
        duration.setText(currentAnnoucement.getDuration());

        distance = (TextView) listItem.findViewById(R.id.distance_textView);
        distance.setText(currentAnnoucement.getDistance());

        Button acceptBtn = listItem.findViewById(R.id.accept);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aContext.startActivity(new Intent(aContext, JobDetailsForClientActivity.class));
            }
        });



        return listItem;



    }


}
