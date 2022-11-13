package com.example.bricoli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.bricoli.databinding.ActivityHomeForClientBinding;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

public class HomeActivityForClient extends AppCompatActivity {

    private ActivityHomeForClientBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home_for_client);
        binding = ActivityHomeForClientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Fragments
        replaceFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.navigationList);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(3);

        //bottom_navigation(fragments)
        binding.navView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.navigationHome:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.navigationList:
                    replaceFragment(new ListFragment());
                    break;
                case R.id.navigationHistory:
                    replaceFragment(new HistoryFragment());
                    break;
                case R.id.navigationUser:
                    replaceFragment(new UserFragment());
                    break;
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}