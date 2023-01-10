package com.example.bricoli.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bricoli.R;
import com.example.bricoli.activities.JobDetailsForClientActivity;
import com.example.bricoli.enumeration.OfferState;
import com.example.bricoli.enumeration.PostulationState;
import com.example.bricoli.models.Annoucement;
import com.example.bricoli.models.Offer;
import com.example.bricoli.models.Postulation;
import com.example.bricoli.retrofit.OfferApi;
import com.example.bricoli.retrofit.PostulationApi;
import com.example.bricoli.retrofit.RetrofitService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeBidsAdapter extends ArrayAdapter<Postulation> {

    private Context aContext ;
    private List<Postulation> postulationArrayList = new ArrayList<>();

    public HomeBidsAdapter(@NotNull Context context , ArrayList<Postulation> list){
        super(context,0,list);
        aContext = context ;
        postulationArrayList = list ;

    }

    @NotNull
    @Override
    public View getView(int position , @Nullable View convertView , @NonNull ViewGroup parent){
        View listItem = convertView ;
        if(listItem == null){
            listItem = LayoutInflater.from(aContext).inflate(R.layout.list_item_for_home_bids,parent,false);
        }

        Postulation currentpostulation = postulationArrayList.get(position);

        ImageView avatar = (ImageView) listItem.findViewById(R.id.worker_image);
        avatar.setImageResource(R.drawable.userphoto);

        TextView fullname , rate , city , price , duration , distance ;

        fullname = (TextView) listItem.findViewById(R.id.nom_complet);
        fullname.setText(currentpostulation.getWorker().getFullName());

        rate = (TextView) listItem.findViewById(R.id.rate_textView);
        rate.setText(currentpostulation.getWorker().getNumberOfRating()+")");
        rate.setText(""+round((double)currentpostulation.getWorker().getSommeRating()/currentpostulation.getWorker().getNumberOfRating(), 1));


        city = (TextView) listItem.findViewById(R.id.city_textView);
        city.setText("city");

        price = (TextView) listItem.findViewById(R.id.price_textView);
        //price.setText(currentpostulation.getPrice());

        duration = (TextView) listItem.findViewById(R.id.days_textView);
        duration.setText("2jrs");

        distance = (TextView) listItem.findViewById(R.id.distance_textView);
        distance.setText("900m");

        Button acceptBtn = listItem.findViewById(R.id.accept);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callUpdatePostulationApiBids(currentpostulation);
                callUpdateOfferApiBids(currentpostulation.getOffer());
                aContext.startActivity(new Intent(aContext, JobDetailsForClientActivity.class));
            }
        });



        return listItem;



    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
    private void callUpdatePostulationApiBids(Postulation postulation){
        postulation.setCreatedAt(null);
        postulation.getOffer().setCreatedAt(null);
        postulation.setState(PostulationState.ACCEPTED.toString());
        RetrofitService retrofit = new RetrofitService();
        PostulationApi postulationApi = retrofit.getRetrofit().create(PostulationApi.class);
        Call<Postulation> call=postulationApi.updatePostulation(postulation.getPostulationId(),postulation);
        call.enqueue(new Callback<Postulation>() {
            @Override
            public void onResponse(Call<Postulation> call, Response<Postulation> response) {
                System.out.println(" post updated"+ response.code());
            }

            @Override
            public void onFailure(Call<Postulation> call, Throwable t) {
                Log.d("postulation update","*********************** Echec");
            }
        });
    }
    private void callUpdateOfferApiBids(Offer offer){
        offer.setCreatedAt(null);
        offer.setState(OfferState.EN_COURS_EXECUTION.toString());
        RetrofitService retrofit = new RetrofitService();
        OfferApi offerApi = retrofit.getRetrofit().create(OfferApi.class);
        Call<Offer> call=offerApi.updateOffer(offer.getOfferId(),offer);
        call.enqueue(new Callback<Offer>() {
            @Override
            public void onResponse(Call<Offer> call, Response<Offer> response) {
                System.out.println(" offer updated"+ response.code());
            }

            @Override
            public void onFailure(Call<Offer> call, Throwable t) {
                Log.d("offer update","*********************** Echec");
            }
        });
    }


}
