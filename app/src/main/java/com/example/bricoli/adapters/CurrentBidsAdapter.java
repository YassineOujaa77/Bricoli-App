package com.example.bricoli.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.bricoli.R;
import com.example.bricoli.models.Bid;

import java.util.ArrayList;

public class CurrentBidsAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Bid> Bids;
    LayoutInflater inflater;

    public CurrentBidsAdapter(Context context, ArrayList<Bid> bids) {
        this.context = context;
        Bids = bids;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Bids.size();
    }

    @Override
    public Object getItem(int i) {
        return Bids.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view=inflater.inflate(R.layout.current_bid_card,null);
        CardView card=view.findViewById(R.id.CardBid);
        ImageView img=view.findViewById(R.id.imageUser);
        TextView txtVille=view.findViewById(R.id.textVille);
        TextView txtNomComplet=view.findViewById(R.id.textNom);
        TextView txtNote=view.findViewById(R.id.textNote);
        ImageView imgStar=view.findViewById(R.id.imageStar);
        TextView txtPrix=view.findViewById(R.id.textPrix);
        TextView txtPriod=view.findViewById(R.id.textJours);
        //TextView txtDistance=view.findViewById(R.id.textDistance);
        Button btn =view.findViewById(R.id.btnState);

        Bid bid=Bids.get(i);
        if(bid.getEtat()=="Waiting")
        {
            card.setCardBackgroundColor(Color.parseColor("#FFFBFB"));
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#17688F")));
            btn.setBackgroundResource(R.drawable.button_reglage);
            btn.setText(bid.getEtat());
            //img.setImageURI(Uri.parse(bid.getPhoto()));
            img.setImageResource(R.drawable.photo);
            txtVille.setText(bid.getVille());
            txtNomComplet.setText(bid.getNomComplet());
            txtNote.setText(bid.getNote());
            imgStar.setImageResource(R.drawable.star);
            txtPrix.setText(bid.getPrix());
            txtPriod.setText(bid.getPeriod());
            //txtDistance.setText(bid.getDistance());
        }
        else if(bid.getEtat()=="Rejected")
        {
            card.setCardBackgroundColor(Color.parseColor("#F49BA4"));
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#DC3545")));
            btn.setBackgroundResource(R.drawable.button_reglage);
            btn.setText(bid.getEtat());
            img.setImageResource(R.drawable.photo);
            //img.setImageURI(Uri.parse(bid.getPhoto()));
            txtVille.setText(bid.getVille());
            txtNomComplet.setText(bid.getNomComplet());
            txtNote.setText(bid.getNote());
            imgStar.setImageResource(R.drawable.star);
            txtPrix.setText(bid.getPrix());
            txtPriod.setText(bid.getPeriod());
            //txtDistance.setText(bid.getDistance());
        }
        else if (bid.getEtat()=="Accepted")
        {
            card.setCardBackgroundColor(Color.parseColor("#70B57F"));
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#218838")));
            btn.setBackgroundResource(R.drawable.button_reglage);
            btn.setText(bid.getEtat());

            //img.setImageURI(Uri.parse(bid.getPhoto()));
            img.setImageResource(R.drawable.photo);
            txtVille.setText(bid.getVille());
            txtNomComplet.setText(bid.getNomComplet());
            txtNote.setText(bid.getNote());
            imgStar.setImageResource(R.drawable.star);
            txtPrix.setText(bid.getPrix());
            txtPriod.setText(bid.getPeriod());
            //txtDistance.setText(bid.getDistance());
        }
        else if(bid.getEtat()=="Finished")
        {
            card.setCardBackgroundColor(Color.parseColor("#70B57F"));
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#218838")));
            btn.setBackgroundResource(R.drawable.button_reglage);
            btn.setText(bid.getEtat());

            //img.setImageURI(Uri.parse(bid.getPhoto()));
            img.setImageResource(R.drawable.photo);
            txtVille.setText(bid.getVille());
            txtNomComplet.setText(bid.getNomComplet());
            txtNote.setText(bid.getNote());
            imgStar.setImageResource(R.drawable.star);
            txtPrix.setText(bid.getPrix());
            txtPriod.setText(bid.getPeriod());
        }

        return view;
    }

}
