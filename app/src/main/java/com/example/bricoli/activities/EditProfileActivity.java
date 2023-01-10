package com.example.bricoli.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.bricoli.R;
import com.example.bricoli.models.Client;
import com.example.bricoli.retrofit.ClientApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.example.bricoli.util.RealPathUtil;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    ImageButton imagePicker;
    String path;
    TextInputLayout fullnameTextView , phoneTextView , addressTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        imagePicker = findViewById(R.id.image_picker);
        fullnameTextView = findViewById(R.id.textInputLayoutFullname);
        phoneTextView = findViewById(R.id.textInputLayoutPhone);
        addressTextView = findViewById(R.id.textInputLayoutAddress);
        Button save = findViewById(R.id.save);



        imagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_PICK);
                    startActivityForResult(intent, 10);
                } else {
                    ActivityCompat.requestPermissions(EditProfileActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }

            }

        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile(path
                        ,fullnameTextView.getEditText().getText().toString()
                        ,phoneTextView.getEditText().getText().toString()
                        ,addressTextView.getEditText().getText().toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            Context context = EditProfileActivity.this;
            path = RealPathUtil.getRealPath(context, uri);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap,200,200,true);
            imagePicker.setImageBitmap(resizedBitmap);

        }
    }


    protected void editProfile(String path , String fullname , String phone , String address){
        RetrofitService retrofitService = new RetrofitService();
        ClientApi clientApi = retrofitService.getRetrofit().create(ClientApi.class);

        Call<Client> call = clientApi.getClient(1L);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                //Toast.makeText(EditProfileActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                Client clientFromDb = new Client(response.body().getUserId()
                        ,                   response.body().getCin()
                        ,                   response.body().getPassword()
                        ,                   response.body().getAddress()
                        ,                   response.body().getSommeRating()
                        ,                   response.body().getNumberOfRating()
                        ,                   response.body().getPhoto()
                        ,                   response.body().getFullName()
                        ,                   response.body().getWorkerField()
                        ,                   response.body().getPhone()
                        ,                   response.body().getToken());


                clientFromDb.setPhoto(path);
                clientFromDb.setFullName(fullname);
                clientFromDb.setAddress(address);
                clientFromDb.setPhone(phone);

                Call<Client> call1 = clientApi.updateClient(clientFromDb,1L);
                call1.enqueue(new Callback<Client>() {
                    @Override
                    public void onResponse(Call<Client> call, Response<Client> response) {
                        startActivity(new Intent(EditProfileActivity.this,SettingActivity.class));
                    }

                    @Override
                    public void onFailure(Call<Client> call, Throwable t) {
                        Toast.makeText(EditProfileActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });



            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        // create client object to send
        //Client client =












    }



}