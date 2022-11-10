package com.example.bricoli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkerHomeActivity extends AppCompatActivity {
    @BindView(R.id.annoucementList)
    ListView announcementList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_home);
        ButterKnife.bind(this);
        ArrayList<Annoucement> annoucements = new ArrayList<>();
        annoucements.add(new Annoucement("Full Name1","Rabat","2.5 (500)","300DH","2 jrs","900m","Je suis à la recherche d'un plombier...........",R.drawable.photo));
        annoucements.add(new Annoucement("Full Name2","Rabat","2.5 (500)","300DH","2 jrs","900m","Je suis à la recherche d'un plombier...........",R.drawable.photo));
        annoucements.add(new Annoucement("Full Name3","Rabat","2.5 (500)","300DH","2 jrs","900m","Je suis à la recherche d'un plombier...........",R.drawable.photo));
        AnnoucementAdapter annoucementAdapter = new AnnoucementAdapter(getApplicationContext(),R.layout.annoucement_cell_layout,annoucements);
        announcementList.setAdapter(annoucementAdapter);
    }
}