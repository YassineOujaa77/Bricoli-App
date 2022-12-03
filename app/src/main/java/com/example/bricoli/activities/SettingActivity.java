package com.example.bricoli.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bricoli.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        editProfile.setOnClickListener(OnEditProfileClick());
        changePasswd.setOnClickListener(OnChangePasswdClick());
        changeRole.setOnClickListener(OnChangeRoleClick());
        addRecentJob.setOnClickListener(OnAddRecentJobClick());
        language.setOnClickListener(OnAddLanguageClick());
        shareWithFriends.setOnClickListener(OnShareWithFriendsClick());
        logOut.setOnClickListener(OnLogOutClick());



        // initialize
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.profile);


        // item from menu selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), ClientHomeActivity.class));
                        return true;
                    case R.id.current:
                        startActivity(new Intent(getApplicationContext(), PostsActuelActivity.class));
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(), ClientHistoryActivity.class));
                        return true;
                    case R.id.profile:
                        return true;
                    default:
                        return false;

                }
            }
        });


    }
    private View.OnClickListener OnEditProfileClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener OnChangePasswdClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener OnChangeRoleClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, ChangeRoleActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener OnAddRecentJobClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, AddRecentJobsPicturesActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener OnAddLanguageClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, LanguageSetting.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener OnShareWithFriendsClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, UserCanInviteFriendsActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener OnLogOutClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };
    }

}