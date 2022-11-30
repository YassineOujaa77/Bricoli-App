package com.example.bricoli.activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bricoli.R;
import com.example.bricoli.adapters.PostsActuelAdapter;

public class PostsActuelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_actuel);
        ListView ListOfPostsActuel = findViewById(R.id.ListOfPostsActuels);

        String []categories={"CategorieX","CategorieX","CategorieX"};
        String []dates={"2 days ago","2 days ago","2 days ago"};
        String []descriptions={"Je suis entrain de chercher un plombier pour peparation d'une ...","Je suis entrain de chercher un plombier pour peparation d'une ...","Je suis entrain de chercher un plombier pour peparation d'une ..."};
        String []nbBids={"5 BIDS","2 BIDS","4 BIDS"};
        String []etats={"negociation","execution","negociation"};
        PostsActuelAdapter adapter=new PostsActuelAdapter(getApplicationContext(),categories,dates,descriptions,nbBids,etats);
        ListOfPostsActuel.setAdapter(adapter);
    }
}