package com.example.bricoli.models;

import com.google.gson.annotations.SerializedName;

public class User
{
    @SerializedName("userId")
    private Long userId;

    @SerializedName("cin")
    private String cin;

    @SerializedName("password")
    private String password;

    @SerializedName("address")
    private String address;

    @SerializedName("sommeRating")
    private Long sommeRating;

    @SerializedName("numberOfRating")
    private Integer numberOfRating;

    @SerializedName("photo")
    private String photo;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("workerField")
    private String workerField;

    @SerializedName("phone")
    private String phone;

    public Long getUserId() {
        return userId;
    }

    public String getCin() {
        return cin;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public Long getSommeRating() {
        return sommeRating;
    }

    public Integer getNumberOfRating() {
        return numberOfRating;
    }

    public String getPhoto() {
        return photo;
    }

    public String getFullName() {
        return fullName;
    }

    public String getWorkerField() {
        return workerField;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", cin='" + cin + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", sommeRating=" + sommeRating +
                ", numberOfRating=" + numberOfRating +
                ", photo='" + photo + '\'' +
                ", fullName='" + fullName + '\'' +
                ", workerField='" + workerField + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
