package com.example.bricoli.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bricoli.R;


public class HistoryAdapter extends BaseAdapter
{
    Context context;
    int [] Images;
    String [] NomComplets;
    String [] Villes;
    String [] Notes;
    String [] Dates;
    String [] Descriptions;
    LayoutInflater inflater;

    public HistoryAdapter(Context context, int[] images, String[] nomComplets, String[] villes, String[] notes, String[] dates, String[] descriptions) {
        this.context = context;
        Images = images;
        NomComplets = nomComplets;
        Villes = villes;
        Notes = notes;
        Dates = dates;
        Descriptions = descriptions;
        inflater=LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return this.Images.length;
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
        view=inflater.inflate(R.layout.historycard,null);
        ImageView img=(ImageView) view.findViewById(R.id.imgWorker);
        TextView nom=(TextView) view.findViewById(R.id.txtNom);
        TextView ville=(TextView) view.findViewById(R.id.txtVille);
        TextView note=(TextView) view.findViewById(R.id.txtNote);
        TextView date=(TextView) view.findViewById(R.id.txtDate);
        TextView description=(TextView) view.findViewById(R.id.txtDescription);
        ImageView imgi1=(ImageView) view.findViewById(R.id.imageView5);
        ImageView imgi2=(ImageView)view.findViewById(R.id.imageView6);

        imgi1.setImageResource(R.drawable.vector);
        imgi2.setImageResource(R.drawable.star);
        img.setImageResource(Images[i]);
        nom.setText(NomComplets[i]);
        ville.setText(Villes[i]);
        note.setText(Notes[i]);
        date.setText(Dates[i]);
        description.setText(Descriptions[i]);
        return view;




    }
}
