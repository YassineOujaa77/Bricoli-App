package com.example.bricoli.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bricoli.R;
import com.example.bricoli.models.History;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class HistoryAdapter extends BaseAdapter
{
    Context context;
    ArrayList<History> Histories;
    LayoutInflater inflater;

    public HistoryAdapter(Context context, ArrayList<History> histories) {
        this.context = context;
        Histories = histories;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Histories.size();
    }

    @Override
    public Object getItem(int i) {
        return Histories.get(i);
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

        History history=Histories.get(i);

        imgi1.setImageResource(R.drawable.vector);
        imgi2.setImageResource(R.drawable.star);
        //img.setImageResource(history.getImage());
        img.setImageURI(Uri.parse(history.getImage()));
        //img.setImageURI(Uri.fromFile(new File("content://media/external/images/media/37")));

        nom.setText(history.getNomComplet());
        ville.setText(history.getVille());
        note.setText(history.getNote());
        date.setText(history.getDate());
        description.setText(history.getDescription());
        return view;




    }

}
