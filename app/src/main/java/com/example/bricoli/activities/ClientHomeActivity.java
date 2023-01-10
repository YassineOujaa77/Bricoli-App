package com.example.bricoli.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.bricoli.R;
import com.example.bricoli.enumeration.Category;
import com.example.bricoli.enumeration.OfferState;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;
import com.example.bricoli.retrofit.ClientApi;
import com.example.bricoli.retrofit.OfferApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        // set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

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

        AutoCompleteTextView autoCompleteTextView;
        autoCompleteTextView = findViewById(R.id.autoCompleteTxt);

        String items [] = getResources().getStringArray(R.array.categories);
        Arrays.sort(items);

        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(ClientHomeActivity.this, R.layout.list_item_for_home_client, items);
        autoCompleteTextView.setAdapter(itemAdapter);

        Button addButton;
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextInputLayout textInputLayout1 = findViewById(R.id.textInputLayout);
                TextInputLayout textInputLayout2 = findViewById(R.id.textInputLayout2);

                String textInputLayoutToString1 = textInputLayout1.getEditText().getText().toString();
                String textInputLayoutToString2 = textInputLayout2.getEditText().getText().toString();

                try {
                    if( !textInputLayoutToString1.equals(("")) && !textInputLayoutToString2.equals((""))){
                        String category = getStringArrayItem(textInputLayoutToString1);
                        String description = textInputLayoutToString2;
                        SharedPreferences preferences = getSharedPreferences("contenu", MODE_PRIVATE);
                        String state=preferences.getString("role","default");
                        Long idUser=preferences.getLong("IdUser",-1l);
                        RetrofitService retrofitClient=new RetrofitService();
                        ClientApi clientApi = retrofitClient.getRetrofit().create(ClientApi.class);
                        clientApi.getClient(idUser).enqueue(new Callback<Client>() {
                            @Override
                            public void onResponse(Call<Client> call, Response<Client> response) {
                                Client client = response.body();
                                Offer offerToAdd =new Offer(category, client, description, OfferState.EN_ATTENTE.toString(), null, null);
                                callAddOfferApi(offerToAdd);
                            }
                            @Override
                            public void onFailure(Call<Client> call, Throwable t) {

                            }
                        });
                        //Client client = new Client(2L, "cin","zzzzz", "adress", 2L, 2, "dfghj", "name", "gghhhhh", "3456788" ,"token");

                    }
                    else{
                        Toast.makeText(ClientHomeActivity.this, getResources().getText(R.string.toast_client_home_catgo_desc), Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e){

                }
            }
        });
    }

    private String getStringArrayItem(String item){
        String items []= getResources().getStringArray(R.array.categories);
        if(item.equals(items[0])){
            return Category.Plomblier.toString();
        }else if(item.equals(items[1])){
            return Category.Organisateur_fetes.toString();
        }else if(item.equals(items[2])){
            return Category.Electricien.toString();
        }else if(item.equals(items[3])){
            return Category.Maçon.toString();
        }else if(item.equals(items[4])){
            return Category.Menuisier.toString();
        }else if(item.equals(items[5])){
            return Category.Forgeron.toString();
        }else if(item.equals(items[6])){
            return Category.Teinturier.toString();
        }else if(item.equals(items[7])){
            return Category.Chef_cuisinier.toString();
        }else if(item.equals(items[8])){
            return Category.Decorateur.toString();
        }
        else {
            return Category.Responsable_nettoyage.toString();
        }
    }

    private void callAddOfferApi(Offer offerToAdd){
        RetrofitService retrofit = new RetrofitService();
        OfferApi offerApi = retrofit.getRetrofit().create(OfferApi.class);
        Call<Offer> call = offerApi.addOffer(offerToAdd);
        call.enqueue(new Callback<Offer>() {
            @Override
            public void onResponse(Call<Offer> call, Response<Offer> response) {
                Toast.makeText(ClientHomeActivity.this, getResources().getText(R.string.toast_client_home_add_offer), Toast.LENGTH_SHORT).show();
                openHomeBidsActivity();
            }
            @Override
            public void onFailure(Call<Offer> call, Throwable t) {
                Toast.makeText(ClientHomeActivity.this, getResources().getText(R.string.toast_client_home_fail_add_offer), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openHomeBidsActivity() {
        Intent intent = new Intent(this, PostsActuelActivity.class);
        startActivity(intent);
    }
}
