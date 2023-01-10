package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bricoli.R;
import com.example.bricoli.enumeration.OfferState;
import com.example.bricoli.fragments.MapFragmentForWorker;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Postulation;
import com.example.bricoli.models.Worker;
import com.example.bricoli.retrofit.ClientApi;
import com.example.bricoli.retrofit.OfferApi;
import com.example.bricoli.retrofit.PostulationApi;
import com.example.bricoli.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobDetailsForWorkerActivity extends AppCompatActivity {
    private static final int CALL_PERMISSION_REQUEST_CODE = 1;
    private Button callbutton;
    private String clientnumber;
    private ImageButton imageButton;
    private Postulation postulation;
    //get postulation from intent

    public void openMap(){
        Intent intent=new Intent(this, MapForWorkerActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details_for_worker);
        //read from intent
        //Intent intent = getIntent();
        //this.postulation = (Postulation) intent.getSerializableExtra("postulationToJobDetailsActivity");
        Worker worker = new Worker(1L,"cin","pass","adress",10L,1,"ggg","fullName","ggggg","666666");
        Client client = new Client(1L,"cin","zzzzz","adress",2L,2,"dfghj","name","gghhhhh","3456788");
        Offer offer = new Offer(5L,"Plomblier",client,"ttttt","EN_COURS_NEGOCIATION",null,null);
        this.postulation = new Postulation(552L,3L,4,"WAITING",null,worker,offer);
        fillJobDetailsForWorker(this.postulation);
        Button finishButton = (Button) findViewById(R.id.job_details_worker_finishbutton);
        finishButton.setOnClickListener(onfinishButtonClicked());
        //initialize the number
        clientnumber="0658601214";
        callbutton = findViewById(R.id.callbutton);

        Fragment fragment= new MapFragmentForWorker();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });

        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+clientnumber));

                if (ActivityCompat.checkSelfPermission(JobDetailsForWorkerActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    askForCallPermission();
                    return;
                }
                startActivity(callIntent);
            }
        });
    }
    private void fillJobDetailsForWorker(Postulation postulation){
        TextView fullName = (TextView) findViewById(R.id.textView);
        TextView rating = (TextView) findViewById(R.id.rateplusnbr);
        TextView price = (TextView) findViewById(R.id.tv_price);
        TextView duration = (TextView) findViewById(R.id.job_details_duration);
        Client client = postulation.getOffer().getClient();
        fullName.setText(client.getFullName());
        Long rate =  client.getSommeRating()/client.getNumberOfRating();
        rating.setText(rate + "( "+client.getNumberOfRating()+" )");
        price.setText(Float.toString(postulation.getPrice()) + "DH");
        duration.setText(Integer.toString(postulation.getDuration()));
    }



    public void askForCallPermission() {
        ActivityCompat.requestPermissions(JobDetailsForWorkerActivity.this,
                new String[]{Manifest.permission.CALL_PHONE},
                CALL_PERMISSION_REQUEST_CODE);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + clientnumber));
                startActivity(callIntent);
            } else {
                Toast.makeText(JobDetailsForWorkerActivity.this, "You cannot call without accepting this permission.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private View.OnClickListener onfinishButtonClicked(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RatingBar ratingBar =(RatingBar)findViewById(R.id.ratingBar);
                Worker worker = new Worker(1L,"cin","pass","adress",10L,1,"ggg","fullName","ggggg","666666");
                Client client = new Client(1L,"cin","zzzzz","adress",2L,2,"dfghj","name","gghhhhh","3456788");
                Offer offer = new Offer(5L,"Plomblier",client,"ttttt","EN_COURS_NEGOCIATION",null,null);
                Postulation postulation = new Postulation(552L,3L,4,"WAITING",null,worker,offer);
                //read from intent
                //Intent intent = getIntent();
                //this.postulation = (Postulation) intent.getSerializableExtra("postulationToJobDetailsActivity");
                callUpdateClientApi(client,(long)ratingBar.getRating());
                callUpdatePostulationApi(postulation);
                callUpdateOfferApi(offer);


            }
        };
    }
    private void callUpdateClientApi(Client client,Long addToSommeRating){
        client.setNumberOfRating(client.getNumberOfRating() + 1);
        Long newRating = client.getSommeRating() + addToSommeRating;
        client.setSommeRating(newRating);
        RetrofitService retrofit = new RetrofitService();
        ClientApi clientApi = retrofit.getRetrofit().create(ClientApi.class);
        Call<Client> call=clientApi.updateClient(client,client.getUserId());
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.d("client update","*********************** Echec");
            }
        });
    }
    private void callUpdatePostulationApi(Postulation postulation){
        postulation.setCreatedAt(null);
        postulation.getOffer().setCreatedAt(null);
        postulation.setState(OfferState.FINISHED.toString());
        RetrofitService retrofit = new RetrofitService();
        PostulationApi postulationApi = retrofit.getRetrofit().create(PostulationApi.class);
        Call<Postulation> call=postulationApi.updatePostulation(postulation.getPostulationId(),postulation);
        call.enqueue(new Callback<Postulation>() {
            @Override
            public void onResponse(Call<Postulation> call, Response<Postulation> response) {
            }

            @Override
            public void onFailure(Call<Postulation> call, Throwable t) {
                Log.d("postulation update","*********************** Echec");
            }
        });
    }
    private void callUpdateOfferApi(Offer offer){
        offer.setCreatedAt(null);
        offer.setState(OfferState.FINISHED.toString());
        RetrofitService retrofit = new RetrofitService();
        OfferApi offerApi = retrofit.getRetrofit().create(OfferApi.class);
        Call<Offer> call=offerApi.updateOffer(offer.getOfferId(),offer);
        call.enqueue(new Callback<Offer>() {
            @Override
            public void onResponse(Call<Offer> call, Response<Offer> response) {
            }

            @Override
            public void onFailure(Call<Offer> call, Throwable t) {
                Log.d("offer update","*********************** Echec");
            }
        });
    }


}