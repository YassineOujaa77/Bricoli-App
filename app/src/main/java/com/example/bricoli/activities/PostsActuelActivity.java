package com.example.bricoli.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.annotation.SuppressLint;
import android.view.MenuItem;

import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bricoli.R;
import com.example.bricoli.adapters.PostsActuelAdapter;
import com.example.bricoli.models.Post;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class PostsActuelActivity extends AppCompatActivity {

    String []categories={"CategorieX","CategorieX","CategorieX"};
    String []periodes={"2 days ago","2 days ago","2 days ago"};
    String []descriptions={"Je suis entrain de chercher un plombier pour peparation d'une ...","Je suis entrain de chercher un plombier pour peparation d'une ...","Je suis entrain de chercher un plombier pour peparation d'une ..."};
    String []nbBids={"5 BIDS","2 BIDS","4 BIDS"};
    String []etats={"Encours Negociation","Encours Execution","Encours Negociation"};
    ListView list;
    ArrayList<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_actuel);

        list=findViewById(R.id.PostsList);
        posts=new ArrayList<Post>();
        for(int i=0;i<categories.length;i++)
        {
            Post post=new Post(categories[i],periodes[i],descriptions[i],nbBids[i],etats[i]);
            posts.add(post);
        }
        PostsActuelAdapter adapter=new PostsActuelAdapter(getApplicationContext(),posts);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txt=view.findViewById(R.id.textEtat);
                if(txt.getText()=="Encours Negociation")
                {
                    startActivity(new Intent(PostsActuelActivity.this,HomeBidsActivity.class));
                }
                else
                {
                    startActivity(new Intent(PostsActuelActivity.this,JobDetailsForClientActivity.class));
                }
            }
        });

        // initialize
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.current);

        // item from menu selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.current:
                        startActivity(new Intent(getApplicationContext(), PostsActuelActivity.class));
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), ClientHistoryActivity.class));
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                        return true;
                    default:
                        return false;

                }
            }
        });


    }
}