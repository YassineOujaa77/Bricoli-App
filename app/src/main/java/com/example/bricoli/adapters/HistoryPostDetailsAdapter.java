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
import com.example.bricoli.models.Annoucement;

import java.util.ArrayList;
import java.util.List;

public class HistoryPostDetailsAdapter extends ArrayAdapter<Annoucement> {

    private Context aContext ;
    private List<Annoucement> annoucementsList = new ArrayList<>();

    public HistoryPostDetailsAdapter(@NonNull Context context, @NonNull ArrayList<Annoucement> List) {

        super(context, 0, List);
        aContext = context ;
        annoucementsList = List;
    }

    @NonNull
    @Override
    public View getView(int position , @Nullable View convertView , @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(aContext).inflate(R.layout.history_post_detail_card, parent, false);
        }

        Annoucement currentAnnoucement = annoucementsList.get(position);

        ImageView avatar=(ImageView) listItem.findViewById(R.id.worker_image);
        avatar.setImageResource(currentAnnoucement.getPhoto());

        TextView fullname, rate, price1, price2, duration1, duration2, description ;

        fullname = (TextView) listItem.findViewById(R.id.nom_complet);
        fullname.setText(currentAnnoucement.getFullName());

        rate = (TextView) listItem.findViewById(R.id.rate_textView);
        rate.setText(currentAnnoucement.getRating());

        price1 = (TextView) listItem.findViewById(R.id.price_textView);
        price1.setText(currentAnnoucement.getPrice());

        price2 = (TextView) listItem.findViewById(R.id.price_textView2);
        price2.setText(currentAnnoucement.getPrice());

        duration1 = (TextView) listItem.findViewById(R.id.work_duration_textView);
        duration1.setText(currentAnnoucement.getDuration());

        duration2 = (TextView) listItem.findViewById(R.id.work_duration_textView2);
        duration2.setText(currentAnnoucement.getDuration());

        description=(TextView) listItem.findViewById(R.id.work_description);
        description.setText(currentAnnoucement.getDescription());

        return listItem;
    }
}
