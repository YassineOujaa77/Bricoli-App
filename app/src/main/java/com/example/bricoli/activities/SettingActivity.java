package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bricoli.R;

import butterknife.BindView;

public class SettingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ImageView editProfile  = (ImageView) findViewById(R.id.edit_profile_icon);
        ImageView changePasswd  = (ImageView) findViewById(R.id.change_passwd_icon);
        ImageView changeRole  = (ImageView) findViewById(R.id.change_role_icon);
        ImageView addRecentJob  = (ImageView) findViewById(R.id.add_recent_job_icon);
        ImageView language  = (ImageView) findViewById(R.id.language_icon);
        ImageView shareWithFriends  = (ImageView) findViewById(R.id.share_with_friends_icon);
        Button logOut  = (Button) findViewById(R.id.log_out);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(SettingActivity.this, EditProfileActivity.class);
                //startActivity(intent);
            }
        });
        changePasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        changeRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, ChangeRoleActivity.class);
                startActivity(intent);
            }
        });
        addRecentJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(SettingActivity.this, AddRecentJobActivity.class);
                //startActivity(intent);
            }
        });
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(SettingActivity.this, LanguageActivity.class);
                //startActivity(intent);
            }
        });
        shareWithFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(SettingActivity.this, ShareWithFriendsActivity.class);
                //startActivity(intent);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}