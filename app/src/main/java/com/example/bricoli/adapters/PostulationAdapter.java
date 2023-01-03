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

import java.util.ArrayList;
import java.util.List;

public class PostulationAdapter extends ArrayAdapter<Postulation> {

    private Context aContext ;
    private List<Postulation> postulationsList;
    private ListView listView;

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

        //Float price = currentPostulation.getPrice();

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

        listView = (ListView) convertView.findViewById(R.id.history_listview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Postulation postulationClicked = (Postulation) postulationsList.get(position);
                Intent announcementIntent = new Intent(aContext, HistoryPostDetailsActivity.class);
                announcementIntent.putExtra("postulation", (CharSequence) postulationClicked);
                //announcementIntent.putExtra("postulationPrice", price);
                aContext.startActivity(announcementIntent);
            }
        });

        return listItem;

    }
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
