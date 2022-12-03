package com.example.bricoli.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.annotation.SuppressLint;
import android.view.MenuItem;

import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bricoli.R;
import com.example.bricoli.adapters.PostsActuelAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PostsActuelActivity extends AppCompatActivity {

    String []categories={"CategorieX","CategorieX","CategorieX"};
    String []dates={"2 days ago","2 days ago","2 days ago"};
    String []descriptions={"Je suis entrain de chercher un plombier pour peparation d'une ...","Je suis entrain de chercher un plombier pour peparation d'une ...","Je suis entrain de chercher un plombier pour peparation d'une ..."};
    String []nbBids={"5 BIDS","2 BIDS","4 BIDS"};
    String []etats={"negociation","execution","negociation"};
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_actuel);
        list=findViewById(R.id.listOfPosts);

        PostsActuelAdapter adapter=new PostsActuelAdapter(getApplicationContext(),categories,dates,descriptions,nbBids,etats);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Button btn=view.findViewById(R.id.btnNegociation);
                if(btn.getText()=="Encours Negociation")
                {
                    //startActivity(new Intent(CurrentBidsActivity.this,TestActivity.class));
                    startActivity(new Intent(PostsActuelActivity.this,HomeBidsActivity.class));

                }
                else if(btn.getText()=="Encours Execution")
                {
                    startActivity(new Intent(PostsActuelActivity.this,Job_Information_plus_maps.class));

                }
            }
        });









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