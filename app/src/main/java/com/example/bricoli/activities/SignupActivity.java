package com.example.bricoli.activities;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.bricoli.R;
import com.example.bricoli.models.Client;
import com.example.bricoli.models.User;
import com.example.bricoli.models.Worker;
import com.example.bricoli.retrofit.ClientApi;
import com.example.bricoli.retrofit.RetrofitService;
import com.example.bricoli.retrofit.RetrofitServiceForClient;
import com.example.bricoli.retrofit.RetrofitServiceForWorker;
import com.example.bricoli.retrofit.WorkerApi;
import com.example.bricoli.util.CryptingMethod;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.location.Geocoder;

import java.io.IOException;

import java.util.List;
import java.util.Locale;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.android.material.textfield.TextInputLayout;

import android.provider.MediaStore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText addresstofillautomatically;
    String ville;
    String country;
    String adresseapresville;
    String adresseparlatitude;// format: latitude/longitude
    private LocationRequest locationRequest;
    private TextInputEditText fullnameEditText;
    private TextInputEditText phoneEditText;
    private TextInputEditText cinEditText;
    private TextInputEditText passwordEditText;
    private TextInputEditText passwordConfirmationEditText;
    private Button signupButton;

    private TextInputLayout fullnameTextInputLayout;
    private TextInputLayout phoneTextInputLayout;
    private TextInputLayout addressTextInputLayout;
    private TextInputLayout cinTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private TextInputLayout passwordConfirmationTextInputLayout;

    private Boolean clientOrWorker;

    private final int GALLERY_REQ_CODE = 1000;
    private String URI = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        addresstofillautomatically = findViewById(R.id.Adress);
        addresstofillautomatically.setEnabled(false);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        fullnameEditText = findViewById(R.id.nameComplet);
        phoneEditText = findViewById(R.id.Phone);
        cinEditText = findViewById(R.id.Cin);
        passwordEditText = findViewById(R.id.Password);
        passwordConfirmationEditText = findViewById(R.id.PasswordConfirmation);

        fullnameTextInputLayout = findViewById(R.id.textInputLayoutname);
        phoneTextInputLayout = findViewById(R.id.textInputLayoutPhone);
        cinTextInputLayout = findViewById(R.id.textInputLayoutCin);
        passwordTextInputLayout = findViewById(R.id.textInputLayoutPassword);
        passwordConfirmationTextInputLayout = findViewById(R.id.textInputLayoutPasswordConfirmation);

        signupButton = findViewById(R.id.Signup);

        // Disable the signup button initially
        signupButton.setEnabled(false);

        // Add a text changed listener to the input fields to enable the signup button when all fields are filled out correctly
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkFieldsForEmptyValues();
            }
        };
        //every time we change we will look if all fields are not empty
        fullnameEditText.addTextChangedListener(textWatcher);
        phoneEditText.addTextChangedListener(textWatcher);
        cinEditText.addTextChangedListener(textWatcher);
        passwordEditText.addTextChangedListener(textWatcher);
        passwordConfirmationEditText.addTextChangedListener(textWatcher);


        setRequiredField(fullnameEditText, fullnameTextInputLayout);
        setRequiredField(phoneEditText, phoneTextInputLayout);
        setRequiredField(cinEditText, cinTextInputLayout);
        setRequiredField(passwordEditText, passwordTextInputLayout);
        setRequiredField(passwordConfirmationEditText, passwordConfirmationTextInputLayout);

        setPhoneNumberValidator(phoneEditText, phoneTextInputLayout);
        validateCinLength(cinEditText, cinTextInputLayout);

        Button addPicture = findViewById(R.id.upload_image_button);

        //for tha password verification
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validatePasswordMatch(passwordEditText, passwordConfirmationEditText, passwordTextInputLayout, passwordConfirmationTextInputLayout);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        passwordConfirmationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validatePasswordMatch(passwordEditText, passwordConfirmationEditText, passwordTextInputLayout, passwordConfirmationTextInputLayout);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        getCurrentLocation();

        // When user click on Add picture button , he should be able to import images
        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setType("image/*");
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);

            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullnameEditText.clearFocus();
                cinEditText.clearFocus();
                phoneEditText.clearFocus();
                passwordEditText.clearFocus();
                passwordConfirmationEditText.clearFocus();
                // Check if any of the fields have an error
                if (passwordTextInputLayout.getError() != null ||
                        passwordConfirmationTextInputLayout.getError() != null || cinTextInputLayout.getError() != null
                        || phoneTextInputLayout.getError() != null || fullnameTextInputLayout.getError() != null || URI.isEmpty()) {
                    // One of the fields has an error, so disable the button
                    signupButton.setEnabled(false);
                } else {
                    // No errors present, so enable the button
                    signupButton.setEnabled(true);

                    //here we create the coresponding object worker or client
                    String fullname = fullnameEditText.getText().toString();
                    String phone = phoneEditText.getText().toString();
                    String cin = cinEditText.getText().toString();
                    String password = null;
                    try {
                        password = CryptingMethod.encrypt(passwordEditText.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    if (clientOrWorker){

                        Client client = new Client(cin, password, adresseparlatitude, 0L, 0, URI,fullname,  "", phone);
                        handleClientOrWorker(clientOrWorker,client,null);

                    }else{

                        Worker worker = new Worker(cin, password, adresseparlatitude, 0L, 0, URI,fullname,  "", phone);
                        handleClientOrWorker(clientOrWorker,null,worker);
                    }

                        // Start the new activity here
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        clientOrWorker = true; //it means client
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_button_client) {
                    clientOrWorker = true;
                } else if (checkedId == R.id.radio_button_worker) {
                    clientOrWorker = false;
                }
            }
        });


    }


    // functions for the gps
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {

                    getCurrentLocation();

                } else {

                    turnOnGPS();
                }
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {

                getCurrentLocation();
            }
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                Uri selectedImageUri = data.getData();
                URI = selectedImageUri.toString();
                checkFieldsForEmptyValues();

            }
        }
    }

    private void getCurrentLocation() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(SignupActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {

                    LocationServices.getFusedLocationProviderClient(SignupActivity.this)
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(SignupActivity.this)
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() > 0) {

                                        int index = locationResult.getLocations().size() - 1;
                                        double latitude = locationResult.getLocations().get(index).getLatitude();
                                        double longitude = locationResult.getLocations().get(index).getLongitude();

                                        Geocoder geocoder = new Geocoder(SignupActivity.this, Locale.getDefault());
                                        List<Address> addresses = null;
                                        try {
                                            addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                            ville = addresses.get(0).getLocality();
                                            country = addresses.get(0).getCountryName();
                                            adresseapresville = addresses.get(0).getAddressLine(0);
                                            adresseparlatitude = latitude + "";
                                            adresseparlatitude.concat("/");
                                            adresseparlatitude.concat(longitude + "");
                                            adresseparlatitude.concat(","+ville);
                                            addresstofillautomatically.setText(country + "," + ville + "," + adresseapresville);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }
                            }, Looper.getMainLooper());

                } else {
                    turnOnGPS();
                }

            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    private void turnOnGPS() {


        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(SignupActivity.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(SignupActivity.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });

    }

    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }


    private void setRequiredField(TextInputEditText editText, TextInputLayout textInputLayout) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    // Validate the field
                    if (TextUtils.isEmpty(editText.getText()) || TextUtils.isEmpty(editText.getText().toString().trim())) {
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("This field is required");
                    } else {
                        textInputLayout.setErrorEnabled(false);
                        textInputLayout.setError(null);
                    }

                }
            }
        });
    }

    private void validatePasswordMatch(TextInputEditText passwordField, TextInputEditText confirmPasswordField, TextInputLayout passwordLayout, TextInputLayout confirmPasswordLayout) {
        int minPasswordLength = 8; // Set minimum password length here
        if (isPasswordMatch(passwordField, confirmPasswordField)) {
            // Passwords match, clear any errors
            passwordLayout.setErrorEnabled(false);
            confirmPasswordLayout.setErrorEnabled(false);

        } else {
            // Passwords do not match, show an error message
            passwordLayout.setErrorEnabled(true);
            passwordLayout.setError("Passwords do not match");
            confirmPasswordLayout.setErrorEnabled(true);
            confirmPasswordLayout.setError("Passwords do not match");
        }
        //also look for the length
        if (passwordField.length() < minPasswordLength) {
            // Password is too short
            passwordLayout.setErrorEnabled(true);
            passwordLayout.setError("Password must be at least " + minPasswordLength + " characters long");
            passwordLayout.setEndIconMode(1);
            passwordLayout.setEndIconDrawable(null);
        }
    }

    private boolean isPasswordMatch(TextInputEditText passwordField, TextInputEditText confirmPasswordField) {
        String password = passwordField.getText().toString();
        String confirmPassword = confirmPasswordField.getText().toString();
        return password.equals(confirmPassword);
    }

    void checkFieldsForEmptyValues() {
        String fullname = fullnameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String cin = cinEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordConfirmation = passwordConfirmationEditText.getText().toString();

        if (TextUtils.isEmpty(fullname) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(cin) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(passwordConfirmation) || !(isPasswordMatch(passwordEditText, passwordConfirmationEditText)) || URI.isEmpty()) {
            signupButton.setEnabled(false);
        } else {
            signupButton.setEnabled(true);
        }
    }

    private void setPhoneNumberValidator(TextInputEditText phoneNumberEditText, TextInputLayout textInputLayout) {
        phoneNumberEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    // Validate the phone number
                    if (phoneNumberEditText.getText().length() != 9) {
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("Phone number must be 9 characters long");
                    } else {
                        textInputLayout.setErrorEnabled(false);
                        textInputLayout.setError(null);
                    }
                }
            }
        });
    }


    private void validateCinLength(TextInputEditText cinEditText, TextInputLayout cinTextInputLayout) {
        cinEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    // Validate the field
                    String cin = cinEditText.getText().toString();
                    if (cin.length() < 7 || cin.length() > 8) {
                        cinTextInputLayout.setErrorEnabled(true);
                        cinTextInputLayout.setError("CIN must be 7 or 8 characters long");
                    } else {
                        cinTextInputLayout.setErrorEnabled(false);
                        cinTextInputLayout.setError(null);
                    }
                }
            }
        });
    }

    private void handleClientOrWorker(Boolean isClient, Client client ,Worker worker) {
        if (isClient) {
            RetrofitServiceForClient retrofit = new RetrofitServiceForClient();

            ClientApi clientApi = retrofit.getRetrofit().create(ClientApi.class);
            Call<Client> call = clientApi.addClient(client);
            call.enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    System.out.println(client.toString() + response.code());

                }

                @Override
                public void onFailure(Call<Client> call, Throwable t) {
                    System.out.println("doesn't  works ");
                }
            });
        } else {
            RetrofitServiceForWorker retrofit = new RetrofitServiceForWorker();
            WorkerApi workerApi = retrofit.getRetrofit().create(WorkerApi.class);
            Call<Worker> call = workerApi.addWorker(worker);
            call.enqueue(new Callback<Worker>() {
                @Override
                public void onResponse(Call<Worker> call, Response<Worker> response) {
                    System.out.println(worker.toString());
                }

                @Override
                public void onFailure(Call<Worker> call, Throwable t) {
                    // handle failure
                }
            });


        }
    }
}