package com.example.bricoli.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.bricoli.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.bricoli.databinding.ActivityClientHomeBinding;
import com.google.android.material.textfield.TextInputLayout;

public class ClientHomeActivity extends AppCompatActivity {

    private ActivityClientHomeBinding binding;

    private Button button;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientHomeBinding.inflate(getLayoutInflater());
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
                }
                return false;
            }
        });

        TextInputLayout textInputLayout;
        AutoCompleteTextView autoCompleteTextView;
        ArrayAdapter<String> adapterItems;

        //spinner123
        textInputLayout=findViewById(R.id.textInputLayout);
        autoCompleteTextView=findViewById(R.id.autoCompleteTxt);

        String[] items = {"Tech","Organisateur de fêtes", "Plombier"};
        ArrayAdapter<String> itemAdapter=new ArrayAdapter<>(ClientHomeActivity.this, R.layout.list_item_for_home_client, items);
        autoCompleteTextView.setAdapter(itemAdapter);
    }

    public void openHomeBidsActivity() {
        Intent intent = new Intent(this, HomeBidsActivity.class);
        startActivity(intent);
    }
}
