package com.example.bricoli.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bricoli.R;
import com.example.bricoli.adapters.PostsActuelAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
                        startActivity(new Intent(getApplicationContext(), ClientHomeActivity.class));
                        return true;
                    case R.id.current:
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