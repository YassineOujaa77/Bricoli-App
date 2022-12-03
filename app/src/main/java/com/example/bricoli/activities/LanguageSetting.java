package com.example.bricoli.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.OnConfigurationChangedProvider;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.bricoli.R;

import java.util.Locale;

public class LanguageSetting extends AppCompatActivity {

    TextView settingLanguage;
    RadioGroup languageradio;
    RadioButton engbutton,frbutton,arbutton;
    static int languagecode;
    Button savebutton;
    public void opentestlanguagepage(){
        Intent intent=new Intent(this, SettingActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_setting);




        settingLanguage = findViewById(R.id.textView10);
        languageradio = findViewById(R.id.language_radiogroup);
        engbutton = findViewById(R.id.l_english);
        frbutton = findViewById(R.id.l_french);
        arbutton = findViewById(R.id.l_arabic);
        savebutton = findViewById(R.id.savebutton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentestlanguagepage();
            }
        });
        languageradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.l_english:
                        String mylanguage="en";
                        languagecode=2;
                        setlocal(mylanguage);
                        break;
                    case R.id.l_arabic:
                        languagecode=1;
                        setlocal("ar");
                        break;
                    case R.id.l_french:
                        languagecode=3;
                        setlocal("fr");
                        break;

                }

            }
        });

    }

    private void setlocal(String mylanguage) {
        Resources resourses = getResources();
        DisplayMetrics metrics=resourses.getDisplayMetrics();
        Configuration config = resourses.getConfiguration();
        config.locale= new Locale(mylanguage);
        resourses.updateConfiguration(config,metrics);
        onConfigurationChanged(config);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        settingLanguage.setText(R.string.language);
        savebutton.setText(R.string.save);


    }

}