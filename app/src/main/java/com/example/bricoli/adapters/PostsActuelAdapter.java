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

import androidx.cardview.widget.CardView;

import com.example.bricoli.R;
import com.example.bricoli.models.Post;

import java.util.ArrayList;

public class PostsActuelAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Post> Posts;
    LayoutInflater inflater;

    public PostsActuelAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        Posts = posts;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Posts.size();
    }

    @Override
    public Object getItem(int i) {
        return Posts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.post_actuel_card,null);
        TextView categorie=view.findViewById(R.id.textCategorie);
        TextView period=view.findViewById(R.id.textPeriod);
        TextView description=view.findViewById(R.id.textDescription);
        TextView etat=view.findViewById(R.id.textEtat);

        Post post=Posts.get(i);
        if(post.getEtat()=="Encours Negociation")
        {
            categorie.setText(post.getCategorie());
            period.setText(post.getPeriod());
            description.setText(post.getDescription());
            etat.setText(post.getEtat());
            etat.setTextColor(Color.parseColor("#F26868"));
        }
        else if(post.getEtat()=="Encours Execution")
        {
            categorie.setText(post.getCategorie());
            period.setText(post.getPeriod());
            description.setText(post.getDescription());
            etat.setText(post.getEtat());
            etat.setTextColor(Color.parseColor("#ff7f00"));
        }
        else if(post.getEtat()=="En Attente")
        {
            categorie.setText(post.getCategorie());
            period.setText(post.getPeriod());
            description.setText(post.getDescription());
            etat.setText(post.getEtat());
            etat.setTextColor(Color.parseColor("#7393B3"));

        }
        else if(post.getEtat()=="Finished")
        {
            categorie.setText(post.getCategorie());
            period.setText(post.getPeriod());
            description.setText(post.getDescription());
            etat.setText(post.getEtat());
            etat.setTextColor(Color.parseColor("#0BDB45"));

        }
        return view;
    }
}
