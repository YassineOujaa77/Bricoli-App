package com.example.bricoli.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.bricoli.R;

import com.example.bricoli.fragments.MapFragment;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Postulation;
import com.example.bricoli.models.Worker;
import com.example.bricoli.retrofit.OfferApi;
import com.example.bricoli.retrofit.PostulationApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.example.bricoli.retrofit.WorkerApi;
import com.example.bricoli.util.FcmNotificationsSender;
import com.example.bricoli.util.Localisationbyaddress;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobDetailsForClientActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private CardView profilecard;
    private Button finishbutton;
    public static double latitude;
    public static double longitude;
    public static Offer staticoffer;

    public void openMap(){
        Intent intent=new Intent(this, mapactivity.class);
        intent.putExtra("offer",staticoffer);
        startActivity(intent);
    }

    public void sendnotificationbytokentitleandinformations(String mytoken,String title,String informations){
        FcmNotificationsSender notificationsSender = new FcmNotificationsSender(mytoken, title, informations, getApplicationContext(), JobDetailsForClientActivity.this);
        notificationsSender.SendNotifications();
    }

    public void openprofile(Worker worker){
        // on merge should changed by the profile class
        Intent intent=new Intent(this, LoginActivity.class);
        intent.putExtra("worker",worker);
        startActivity(intent);
    }

    public void opennextbyfinish(){
        // on merge should changed by the profile class
        Intent intent=new Intent(this, PostsActuelActivity.class);
        startActivity(intent);
    }

    public void updateworkerratings(int sommerating,Worker workertocopie){
        Long longrating=Long.valueOf(sommerating);
        workertocopie.setNumberOfRating(workertocopie.getNumberOfRating()+1);
        workertocopie.setSommeRating(workertocopie.getSommeRating()+longrating);
        RetrofitService retrofit = new RetrofitService();
        WorkerApi workerApi = retrofit.getRetrofit().create(WorkerApi.class);
        Call<Worker> worker1=workerApi.updateWorker(workertocopie,workertocopie.getUserId());
        worker1.enqueue(new Callback<Worker>() {
            @Override
            public void onResponse(Call<Worker> call, Response<Worker> response) {
                Worker worker11 = response.body();
            }
            @Override
            public void onFailure(Call<Worker> call, Throwable t) {
                System.out.println("failed to work with postulation");

            }
        });}

    public void updateOffrebystate(Offer offretocopie, String state, Date createdat){
        offretocopie.setState(state);
        offretocopie.setCreatedAt(null);
        RetrofitService retrofit = new RetrofitService();
        OfferApi offerApi = retrofit.getRetrofit().create(OfferApi.class);
        Call<Offer> offre1=offerApi.updateOffer(offretocopie.getOfferId(), offretocopie);
        offre1.enqueue(new Callback<Offer>() {
            @Override
            public void onResponse(Call<Offer> call, Response<Offer> response) {
                Offer worker11 = response.body();
            }
            @Override
            public void onFailure(Call<Offer> call, Throwable t) {
                System.out.println("failed to work with postulation");

            }
        });}

    public void getpostulationandupdateit(Postulation postulation){
        RetrofitService retrofit = new RetrofitService();
        PostulationApi postulationapiclick = retrofit.getRetrofit().create(PostulationApi.class);
        postulation.setCreatedAt(null);
        postulation.setState("FINISHED");
        Log.d("we", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!we are in heeeeeeeeeeeeeeeeeeeeeeere");
        Call<Postulation> postulationCall=postulationapiclick.updatePostulation(postulation, postulation.getPostulationId());
        postulationCall.enqueue(new Callback<Postulation>() {
                    @Override
                    public void onResponse(Call<Postulation> call, Response<Postulation> response) {
                        Postulation post = response.body();
                        Log.d("we", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!§§§§§§§§§§§§§§§§§§§§§§§§!!!!!!!!!!!!!!we are in heeeeeeeeeeeeeeeeeeeeeeere");

                    }

                    @Override
                    public void onFailure(Call<Postulation> call, Throwable t) {
                        System.out.println("failed to work with postulation");

                    }
                });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_worker_informations_and_map);

        RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.ratingBar); // initiate a rating bar
        int numberOfStars = simpleRatingBar.getNumStars(); // get total number of stars of rating bar

        System.out.println("*****************************");
        //afficher token
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
                        System.out.println("*****************************");
                        System.out.println(token);
                    }
                });


        Client client=new Client(1L,"30303030","pass","30/40,Marakech",50L,3,""," mohamed benjeloun","","0658601214","this is a token to get ");
        Worker worker=new Worker(1L,"JJ202020","nnnnnnn","20/20,Rabat",10L,100,"gggggg","chaymaa elharti","plombier","0000","token");
        Offer offre=new Offer(3L,"cat",client,"this is an offer","en cours dexecution",null,null);
        staticoffer=offre;




        RetrofitService retrofit = new RetrofitService();
        PostulationApi postulationapi = retrofit.getRetrofit().create(PostulationApi.class);
        postulationapi.getPostulationByOfferIdAndState(offre.getOfferId(),"ENCOURSDEXECUTION").enqueue(new Callback<List<Postulation>>() {
            @Override
            public void onResponse(Call<List<Postulation>> call, Response<List<Postulation>> response) {
                List<Postulation> list=new ArrayList<>();
                list=response.body();
                Log.d("test","!!!!!!***********************found"+list.get(0).getPostulationId());
                Postulation postulation1=list.get(0);
                System.out.println("***********************found postulation*******************");
                TextView price= (TextView) findViewById(R.id.tv_price);
                price.setText(Math.round(postulation1.getPrice())+" DH");
                TextView time= (TextView) findViewById(R.id.daysduration);
                time.setText("   "+postulation1.getDuration()+" ");
                Worker workerfrompost=postulation1.getWorker();
                TextView nom= (TextView) findViewById(R.id.textView);
                nom.setText(workerfrompost.getFullName());
                TextView ratenbr= (TextView) findViewById(R.id.rateplusnbr);
                DecimalFormat df = new DecimalFormat("#.#");
                ratenbr.setText(df.format(workerfrompost.getSommeRating()/workerfrompost.getNumberOfRating())+ " ("+workerfrompost.getSommeRating()+")");

                profilecard = (CardView) findViewById(R.id.profilecardc);
                profilecard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openprofile(workerfrompost);

                    }
                });




            }

            @Override
            public void onFailure(Call<List<Postulation>> call, Throwable t) {

            }
        });

        // initialize
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // item from menu selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),WorkerHomeActivity.class));
                        return true;
                    case R.id.current:
                        startActivity(new Intent(getApplicationContext(), CurrentBidsActivity.class));
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), HistoryOffreActivity.class));
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                        return true;
                    default:
                        return false;

                }
            }
        });

        Localisationbyaddress localisationbyaddress=new Localisationbyaddress();

        String address = worker.getAddress();

        localisationbyaddress.setlalon(address);

        latitude=localisationbyaddress.latitude;

        longitude=localisationbyaddress.longitude;

        finishbutton = (Button) findViewById(R.id.finishbutton);

        finishbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update worker
                PostulationApi postulationapiclick = retrofit.getRetrofit().create(PostulationApi.class);
                postulationapiclick.getPostulationByOfferIdAndState(offre.getOfferId(), "ENCOURSDEXECUTION").enqueue(new Callback<List<Postulation>>() {
                    @Override
                    public void onResponse(Call<List<Postulation>> call, Response<List<Postulation>> response) {
                        List<Postulation> list=new ArrayList<>();
                        list=response.body();
                        Log.d("tag","we found it on click");
                        Postulation postulation1=list.get(0);
                        Worker worker1=postulation1.getWorker();
                        Log.d("tag2","we found it on click "+ worker1.getSommeRating()+"*************");
                        updateworkerratings(numberOfStars,worker1);
                        //update postulation
                        getpostulationandupdateit(postulation1);
                    }
                    @Override
                    public void onFailure(Call<List<Postulation>> call, Throwable t) {
                    }
                });



                //update offer
                updateOffrebystate(offre,"FINISHED",null);

                sendnotificationbytokentitleandinformations("dcFHs3f_QWeLoWzDDep5ay:APA91bFVe6fO3dSsVlNbfZg0nT_laKSrjOYqaEFCV652rUIdIwafaKNHA6bdpHOVpVx4y8o0o3Fn-N-mH1f0C0HigFM3Xv3D_cVsvAxSl4Mr7OggWab2sm-IH18Bgzv_iVRKqU2vJIWq","title","informations");
                //to split localisation

                opennextbyfinish();

            }
        });

        imageButton = (ImageButton) findViewById(R.id.imageButton);


        String address1 = worker.getAddress();
        System.out.println("***************************************");
        System.out.println(address);
        localisationbyaddress.setlalon(address);
        System.out.println(localisationbyaddress.city);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openMap();
            }
        });

        TextView time= (TextView) findViewById(R.id.tv_time);

        Fragment fragment= new MapFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();


    }
}
