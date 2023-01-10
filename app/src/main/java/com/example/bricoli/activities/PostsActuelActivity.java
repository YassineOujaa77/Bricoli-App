package com.example.bricoli.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.bricoli.enumeration.PostulationState;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Post;
import com.example.bricoli.retrofit.OfferApi;
import com.example.bricoli.retrofit.RetrofitServiceForOffer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsActuelActivity extends AppCompatActivity {

    public enum OfferState {
        EN_ATTENTE,
        EN_COURS_EXECUTION,
        EN_COURS_NEGOCIATION,
        FINISHED
    }

    String []categories={"CategorieX","CategorieX","CategorieX"};
    String []periodes={"2 days ago","2 days ago","2 days ago"};
    String []descriptions={"Je suis entrain de chercher un plombier pour peparation d'une ...","Je suis entrain de chercher un plombier pour peparation d'une ...","Je suis entrain de chercher un plombier pour peparation d'une ..."};
    String []nbBids={"5 BIDS","2 BIDS","4 BIDS"};
    String []etats={"Encours Negociation","Encours Execution","Encours Negociation"};
    ListView list;
    ArrayList<Post> posts;
    List<Offer> offers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_actuel);
        list=findViewById(R.id.PostsList);
        offers=new ArrayList<Offer>();
        SharedPreferences preferences = getSharedPreferences("contenu", MODE_PRIVATE);
        String state=preferences.getString("role","default");
        Long idUser=preferences.getLong("IdUser",-1l);

        RetrofitServiceForOffer retrofit=new RetrofitServiceForOffer();
        OfferApi offer=retrofit.getRetrofit().create(OfferApi.class);
        offer.getOfferByClientId(idUser).enqueue(new Callback<List<Offer>>() {
            @Override
            public void onResponse(Call<List<Offer>> call, Response<List<Offer>> response) {
                offers=response.body();

                if(offers.size()!=0)
                {
                    String []Categories=new String[offers.size()];
                    String []Periodes=new String[offers.size()];
                    String []Descriptions=new String[offers.size()];
                    String []Etats=new String[offers.size()];
                    String []States=new String[offers.size()];
                    String []nbrBids=new String[offers.size()];
                    for(int i=0;i<offers.size();i++)
                    {
                        nbrBids[i]="";
                        Categories[i]=offers.get(i).getCategory();
                        Descriptions[i]=offers.get(i).getDescription();
                        Date date1= offers.get(i).getCreatedAt();
                        Date date2=new Date();
                        long time_difference =date2.getTime()-date1.getTime();
                        long days_difference=(time_difference/(1000*60*60*24))%365;
                        Periodes[i]=days_difference+" days ago";
                        Etats[i]=offers.get(i).getState().toString();
                        Log.d("etat","***********************"+Etats[i]);
                        if(Etats[i].equals(OfferState.EN_ATTENTE.toString()))
                        {
                            States[i]="En Attente";
                        }
                        if(Etats[i].equals(OfferState.EN_COURS_EXECUTION.toString()))
                        {
                            States[i]="Encours Execution";
                        }
                        if(Etats[i].equals(OfferState.EN_COURS_NEGOCIATION.toString()))
                        {
                            States[i]="Encours Negociation";
                        }
                        if(Etats[i].equals(OfferState.FINISHED.toString()))
                        {
                            States[i]="Finished";
                        }


                    }

                    posts=new ArrayList<Post>();
                    for(int i=0;i<offers.size();i++)
                    {
                        Post post=new Post(Categories[i],Periodes[i],Descriptions[i],nbrBids[i],States[i]);
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
                                Intent intentHomeBids = new Intent(PostsActuelActivity.this,HomeBidsActivity.class);
                                intentHomeBids.putExtra("idOfferHomeBids",offers.get(i).getOfferId());
                                startActivity(intentHomeBids);
                            }
                            else if(txt.getText()=="Encours Execution")
                            {
                                startActivity(new Intent(PostsActuelActivity.this,JobDetailsForClientActivity.class));
                            }
                        }
                    });





                }
                else
                {

                }

                /*Log.d("offers","************************** "+offers.size());
                Date date1= offers.get(0).getCreatedAt();
                SimpleDateFormat obj=new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
                Date date2=new Date();
                long time_difference =date2.getTime()-date1.getTime();
                long days_difference=(time_difference/(1000*60*60*24))%365;*/
                //Log.d("difference","********************** "+days_difference);

            }

            @Override
            public void onFailure(Call<List<Offer>> call, Throwable t) {
                Log.d("offers","************************** noOffers");
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
                        startActivity(new Intent(getApplicationContext(),ClientHomeActivity.class));
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