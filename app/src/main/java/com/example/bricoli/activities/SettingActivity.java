package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bricoli.R;

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

        editProfile.setOnClickListener(OnEditProfileClick());
        changePasswd.setOnClickListener(OnChangePasswdClick());
        changeRole.setOnClickListener(OnChangeRoleClick());
        addRecentJob.setOnClickListener(OnAddRecentJobClick());
        language.setOnClickListener(OnAddLanguageClick());
        shareWithFriends.setOnClickListener(OnShareWithFriendsClick());
        logOut.setOnClickListener(OnLogOutClick());


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
                //Intent intent = new Intent(SettingActivity.this, LanguageActivity.class);
                //startActivity(intent);
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