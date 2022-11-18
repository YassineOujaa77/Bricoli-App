package com.example.bricoli;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import com.example.bricoli.databinding.ActivityHomeForClientBinding;
import com.google.android.material.textfield.TextInputLayout;

public class HomeActivityForClient extends AppCompatActivity {

    private ActivityHomeForClientBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home_for_client);
        binding = ActivityHomeForClientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Button addButton = findViewById(R.id.addButton);
        TextInputLayout textInputLayout;
        AutoCompleteTextView autoCompleteTextView;
        ArrayAdapter<String> adapterItems;

        //spinner123
        textInputLayout=findViewById(R.id.textInputLayout);
        autoCompleteTextView=findViewById(R.id.autoCompleteTxt);

        String[] items = {"Tech","Organisateur de fêtes", "Plombier"};
        ArrayAdapter<String> itemAdapter=new ArrayAdapter<>(HomeActivityForClient.this, R.layout.list_item, items);
        autoCompleteTextView.setAdapter(itemAdapter);
    }
}