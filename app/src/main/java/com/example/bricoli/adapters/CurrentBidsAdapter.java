package com.example.bricoli.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.bricoli.R;

public class CurrentBidsAdapter extends BaseAdapter
{
    Context context;
    int []Photos;
    String []Villes;
    String []Noms;
    String []Notes;
    String []Prices;
    String []Periodes;
    String []Distances;
    String []Etats;
    LayoutInflater inflater;

    public CurrentBidsAdapter(Context context, int[] photos, String[] villes, String[] noms, String[] notes, String[] prices, String[] periodes, String[] distances, String[] etats)
    {
        this.context = context;
        Photos = photos;
        Villes = villes;
        Noms = noms;
        Notes = notes;
        Prices = prices;
        Periodes = periodes;
        Distances = distances;
        Etats = etats;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.Photos.length;
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
        view=inflater.inflate(R.layout.current_bid_card,null);
        CardView card=view.findViewById(R.id.card);
        ImageView imgUser=view.findViewById(R.id.imgUser);
        TextView txtVille=view.findViewById(R.id.txtVillo);
        TextView txtNomComplet=view.findViewById(R.id.txtNomCompleto);
        ImageView imgEtoile=view.findViewById(R.id.imgStar);
        TextView txtNote=view.findViewById(R.id.txtNotee);
        TextView txtPrix=view.findViewById(R.id.txtPricee);
        TextView txtPeriod=view.findViewById(R.id.txtJour);
        TextView txtDistance=view.findViewById(R.id.txtDistance);
        Button btn =view.findViewById(R.id.btnTest);

        if(Etats[i]=="waiting")
        {
            card.setCardBackgroundColor(Color.parseColor("#FFFBFB"));
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#17688F")));
            btn.setBackgroundResource(R.drawable.button_reglage);
            btn.setText("Waiting");
            imgUser.setImageResource(Photos[i]);
            txtVille.setText(Villes[i]);
            txtNomComplet.setText(Noms[i]);
            imgEtoile.setImageResource(R.drawable.star);
            txtNote.setText(Notes[i]);
            txtPrix.setText(Prices[i]);
            txtPeriod.setText(Periodes[i]);
            txtDistance.setText(Distances[i]);

        }
        else if(Etats[i]=="declined")
        {
            card.setCardBackgroundColor(Color.parseColor("#F49BA4"));
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#DC3545")));
            btn.setBackgroundResource(R.drawable.button_reglage);
            btn.setText("Declined");
            imgUser.setImageResource(Photos[i]);
            txtVille.setText(Villes[i]);
            txtNomComplet.setText(Noms[i]);
            imgEtoile.setImageResource(R.drawable.star);
            txtNote.setText(Notes[i]);
            txtPrix.setText(Prices[i]);
            txtPeriod.setText(Periodes[i]);
            txtDistance.setText(Distances[i]);

        }
        else//Etats[i]==accepted
        {
            card.setCardBackgroundColor(Color.parseColor("#70B57F"));
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#218838")));
            btn.setBackgroundResource(R.drawable.button_reglage);
            btn.setText("Accepted");
            imgUser.setImageResource(Photos[i]);
            txtVille.setText(Villes[i]);
            txtNomComplet.setText(Noms[i]);
            imgEtoile.setImageResource(R.drawable.star);
            txtNote.setText(Notes[i]);
            txtPrix.setText(Prices[i]);
            txtPeriod.setText(Periodes[i]);
            txtDistance.setText(Distances[i]);
        }

        return view;
    }
}
