package com.example.bricoli.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bricoli.R;

public class PostsActuelAdapter extends BaseAdapter
{
    Context context;
    String []Categories;
    String []Dates;
    String []Descriptions;
    String []NbBids;
    String []Etats;
    LayoutInflater inflater;

    public PostsActuelAdapter(Context context, String[] categories, String[] dates, String[] descriptions, String[] nbBids, String[] etats) {
        this.context = context;
        Categories = categories;
        Dates = dates;
        Descriptions = descriptions;
        NbBids = nbBids;
        Etats = etats;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.Categories.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view=inflater.inflate(R.layout.post_actuel_card,null);
        TextView txtCategorie=view.findViewById(R.id.txtCategorie);
        TextView date=view.findViewById(R.id.txtDate);
        TextView description=view.findViewById(R.id.txtDescriptione);
        TextView nbBids=view.findViewById(R.id.txtNbBids);
        ImageView imgEclipse=view.findViewById(R.id.imgEclipse);
        Button btn =view.findViewById(R.id.btnNegociation);

        if(Etats[i]=="negociation")
        {
            nbBids.setText(NbBids[i]);
            imgEclipse.setImageResource(R.drawable.ellipsevert);
            btn.setText("Encours Negociation");
            btn.setTextColor(Color.parseColor("#F26868"));
        }
        else
        {
            imgEclipse.setVisibility(View.INVISIBLE);
            nbBids.setVisibility(View.INVISIBLE);
            btn.setText("Encours Execution");
            btn.setTextColor(Color.parseColor("#0BDB45"));
        }
        txtCategorie.setText(Categories[i]);
        date.setText(Dates[i]);
        description.setText(Descriptions[i]);
        return view;
    }
}
