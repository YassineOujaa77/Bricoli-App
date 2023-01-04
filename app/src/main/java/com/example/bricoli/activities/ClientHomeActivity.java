package com.example.bricoli.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.bricoli.R;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Offer;
import com.example.bricoli.retrofit.OfferApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.bricoli.databinding.ActivityClientHomeBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientHomeActivity extends AppCompatActivity {

    //private ActivityClientHomeBinding binding;
    AutoCompleteTextView autoCompleteTextView;
    private Button addButton;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityClientHomeBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_client_home);

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

        autoCompleteTextView=findViewById(R.id.autoCompleteTxt);

        String[] items = {"Tech","Organisateur de fêtes", "Plombier"};
        ArrayAdapter<String> itemAdapter=new ArrayAdapter<>(ClientHomeActivity.this, R.layout.list_item_for_home_client, items);
        autoCompleteTextView.setAdapter(itemAdapter);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextInputLayout textInputLayout1 = findViewById(R.id.textInputLayout);
                TextInputLayout textInputLayout2 = findViewById(R.id.textInputLayout2);
                try {
                    if( !textInputLayout1.getEditText().getText().toString().equals(("")) && !textInputLayout2.getEditText().getText().toString().equals((""))){
                        String category = textInputLayout1.getEditText().getText().toString();
                        String description = textInputLayout2.getEditText().getText().toString();
                        Client client = new Client(9L, "AE789098","123", "lot 3 rabat", 22L, 11, "photo", "Salma", "testtest", "0667888888" );
                        Offer offerToAdd =new Offer(10L, category, client, description, "finished", new Date(), new HashSet<>());
                        callAddOfferApi(offerToAdd);
                    }
                    else{
                        Toast.makeText(ClientHomeActivity.this, "Please fill both category and description", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e){

                }
            }
        });

    }

    private void callAddOfferApi(Offer offerToAdd){
        RetrofitService retrofit = new RetrofitService();
        OfferApi offerApi = retrofit.getRetrofit().create(OfferApi.class);
        Call<Offer> call=offerApi.addOffer(offerToAdd);
        call.enqueue(new Callback<Offer>() {
            @Override
            public void onResponse(Call<Offer> call, Response<Offer> response) {
                //Toast.makeText(ClientHomeActivity.this, "GOOD", Toast.LENGTH_SHORT).show();
                openHomeBidsActivity();
            }
            @Override
            public void onFailure(Call<Offer> call, Throwable t) {
                Toast.makeText(ClientHomeActivity.this, "Fail in adding offer", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void openHomeBidsActivity() {
        Intent intent = new Intent(this, HomeBidsActivity.class);
        startActivity(intent);
    }
}
