package com.example.bricoli.models;

import java.io.Serializable;

public class User implements Serializable {
    private Long userId;
    private String cin;
    private String password;
    private String address;
    private Long sommeRating;
    private Integer numberOfRating;
    private String photo;
    private String fullName;
    private String workerField;
    private String phone;
    private String token;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getSommeRating() {
        return sommeRating;
    }

    public void setSommeRating(Long sommeRating) {
        this.sommeRating = sommeRating;
    }

    public Integer getNumberOfRating() {
        return numberOfRating;
    }

    public void setNumberOfRating(Integer numberOfRating) {
        this.numberOfRating = numberOfRating;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWorkerField() {
        return workerField;
    }

    public void setWorkerField(String workerField) {
        this.workerField = workerField;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
                ", token='" + token + '\'' +
                '}';
    }
}
