package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.bricoli.R;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.Worker;

public class ChangeRoleActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_role);
        SharedPreferences preferences = getSharedPreferences("contenu", MODE_PRIVATE);
        String state=preferences.getString("role","default");
        Long idUser=preferences.getLong("IdUser",-1l);

        RadioButton client=findViewById(R.id.radio_button_client);
        RadioButton worker=findViewById(R.id.radio_button_worker);
        Button btnSave=findViewById(R.id.btnSave);
        if(state.equals("client"))
        {
            client.setChecked(true);
        }
        else if(state.equals("worker"))
        {
             worker.setChecked(true);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(client.isChecked()==true && state.equals("worker"))
                {



                }
                else if(worker.isChecked()==true && state.equals("client"))
                {

                }
            }
        });


        




        Log.d("Etat","********************************** "+state);
        Log.d("Id","************************* "+idUser);










    }
}