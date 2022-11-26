package com.example.bricoli.models;

public class Annoucement {

    private String fullName;
    private String city;
    private String rating;
    private String price;
    private String duration;
    private String distance;
    private String description;
    private int photo;

    public Annoucement(String fullName, String city, String rating, String price, String duration, String distance, String description , int photo) {
        this.fullName = fullName;
        this.city = city;
        this.rating = rating;
        this.price = price;
        this.duration = duration;
        this.distance = distance;
        this.description = description;
        this.photo = photo;
    }

    public Annoucement(String fullName, String city, String rating, String price, String duration, String distance, int photo) {
        this.fullName = fullName;
        this.city = city;
        this.rating = rating;
        this.price = price;
        this.duration = duration;
        this.distance = distance;
        this.photo = photo;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCity() {
        return city;
    }

    public String getRating() {
        return rating;
    }

    public String getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }

    public String getDistance() {
        return distance;
    }

    public String getDescription() {
        return description;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
