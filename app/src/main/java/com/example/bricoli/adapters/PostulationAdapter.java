package com.example.bricoli.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bricoli.R;
import com.example.bricoli.activities.HistoryPostDetailsActivity;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Postulation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostulationAdapter extends ArrayAdapter<Postulation> {

    private Context aContext ;
    //private List<Postulation> postulationsList;
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

        //Postulation currentPostulation = postulationsList.get(position);

        ImageView avatar=(ImageView) listItem.findViewById(R.id.worker_image);
        avatar.setImageResource(R.drawable.userphoto);
        //avatar.setImageResource(currentOffer.getPhoto());

        TextView fullName , sommeRating , numberOfRating, city , duration , description ;

        //Float price = currentPostulation.getPrice();

        fullName = (TextView) listItem.findViewById(R.id.nom_complet);
        fullName.setText(postulations.get(position).getWorker().getFullName());

        sommeRating = (TextView) listItem.findViewById(R.id.sommeRating_textView);
        sommeRating.setText(""+round((double)postulations.get(position).getWorker().getSommeRating()/postulations.get(position).getWorker().getNumberOfRating(), 1));

        numberOfRating = (TextView) listItem.findViewById(R.id.numberOfRating_textView);
        numberOfRating.setText(" ("+postulations.get(position).getWorker().getNumberOfRating()+") ");

        city = (TextView) listItem.findViewById(R.id.city_textView);
        city.setText("city");

        duration = (TextView) listItem.findViewById(R.id.work_duration_textView);
        duration.setText(""+postulations.get(position).getDuration()+" days");

        description=(TextView) listItem.findViewById(R.id.work_description);
        description.setText(postulations.get(position).getOffer().getDescription());

        //listView = (ListView) convertView.findViewById(R.id.history_listview);

        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Postulation postulationCli = postulations.get(position);
                Postulation postulationClicked =  (Postulation) postulations.get(position);
                Intent postulationIntent = new Intent(aContext, HistoryPostDetailsActivity.class);
                postulationIntent.putExtra("postulation", postulationClicked);
                //announcementIntent.putExtra("postulationPrice", price);
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
