package com.example.bricoli.models;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Postulation
{
    @SerializedName("postulationId")
    private Long postulationId;

    @SerializedName("price")
    private float price;

    @SerializedName("duration")
    private int duration;

    @SerializedName("state")
    private String state;

    @SerializedName("createdAt")
    private LocalDate createdAt;

    @SerializedName("worker")
    private Worker worker;

    @SerializedName("offer")
    private Offer offer;

    public Long getPostulationId() {
        return postulationId;
    }

    public float getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }

    public String getState() {
        return state;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Worker getWorker() {
        return worker;
    }

    public Offer getOffer() {
        return offer;
    }
}
