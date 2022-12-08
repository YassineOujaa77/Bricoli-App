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
import android.widget.LinearLayout;

import com.example.bricoli.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;

public class SettingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        LinearLayout editProfile  = (LinearLayout) findViewById(R.id.edit_profile);
        LinearLayout changePasswd  = (LinearLayout) findViewById(R.id.change_passwd);
        LinearLayout changeRole  = (LinearLayout) findViewById(R.id.change_role);
        LinearLayout addRecentJob  = (LinearLayout) findViewById(R.id.add_recent_job);
        LinearLayout language  = (LinearLayout) findViewById(R.id.language);
        LinearLayout shareWithFriends  = (LinearLayout) findViewById(R.id.share_with_friends);
        Button logOut  = (Button) findViewById(R.id.log_out);

        editProfile.setOnClickListener(onEditProfileClick());
        changePasswd.setOnClickListener(onChangePasswdClick());
        changeRole.setOnClickListener(onChangeRoleClick());
        addRecentJob.setOnClickListener(onAddRecentJobClick());
        language.setOnClickListener(onAddLanguageClick());
        shareWithFriends.setOnClickListener(onShareWithFriendsClick());
        logOut.setOnClickListener(onLogOutClick());



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
    private View.OnClickListener onEditProfileClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener onChangePasswdClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener onChangeRoleClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, ChangeRoleActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener onAddRecentJobClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, AddRecentJobsPicturesActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener onAddLanguageClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, LanguageSetting.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener onShareWithFriendsClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, UserCanInviteFriendsActivity.class);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener onLogOutClick(){
        return new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };
    }

}