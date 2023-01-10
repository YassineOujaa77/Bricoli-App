package com.example.bricoli.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bricoli.R;
import com.example.bricoli.activities.HistoryPostDetailsActivity;
import com.example.bricoli.models.Postulation;

import java.util.ArrayList;

public class PostulationAdapter extends ArrayAdapter<Postulation> {

    private Context aContext ;
    private ArrayList<Postulation> postulations;

    public PostulationAdapter(@NonNull Context context, @NonNull ArrayList<Postulation> postulations) {
        super(context, 0, postulations);
        this.aContext = context ;
        this.postulations = postulations;
    }

    @NonNull
    @Override
    public View getView(int position , @Nullable View convertView , @NonNull ViewGroup parent){
        View listItem = convertView ;
        if(listItem == null){
            listItem = LayoutInflater.from(aContext).inflate(R.layout.history_offre_cell,parent,false);
        }

        ImageView avatar=(ImageView) listItem.findViewById(R.id.worker_image);
        avatar.setImageResource(R.drawable.userphoto);
        //avatar.setImageResource(currentOffer.getPhoto());

        TextView fullName , sommeRating , numberOfRating, city , duration , description ;


        fullName = (TextView) listItem.findViewById(R.id.nom_complet);
        fullName.setText(postulations.get(position).getWorker().getFullName());

        sommeRating = (TextView) listItem.findViewById(R.id.sommeRating_textView);
        sommeRating.setText(""+round((double)postulations.get(position).getWorker().getSommeRating()/postulations.get(position).getWorker().getNumberOfRating(), 1));

        numberOfRating = (TextView) listItem.findViewById(R.id.numberOfRating_textView);
        numberOfRating.setText(" ("+postulations.get(position).getWorker().getNumberOfRating()+") ");

        city = (TextView) listItem.findViewById(R.id.city_textView);
        String[] address = postulations.get(position).getWorker().getAddress().split(",",2);
        if(address.length>=1){
            city.setText(address[1]);
        }

        duration = (TextView) listItem.findViewById(R.id.work_duration_textView);
        duration.setText(""+postulations.get(position).getDuration()+" days");

        description=(TextView) listItem.findViewById(R.id.work_description);
        description.setText(postulations.get(position).getOffer().getDescription());

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Postulation postulationClicked =  (Postulation) postulations.get(position);
                Intent postulationIntent = new Intent(aContext, HistoryPostDetailsActivity.class);
                postulationIntent.putExtra("postulation", postulationClicked);
                aContext.startActivity(postulationIntent);
            }
        });
        return listItem;
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
