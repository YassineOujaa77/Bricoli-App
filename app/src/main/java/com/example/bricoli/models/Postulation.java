package com.example.bricoli.models;

import java.util.Date;

public class Postulation {

    private Long postulationId;
    private float price;
    private int duration;
    private String state;
    private Date createdAt;
    private Worker worker;
    private Offer offer;

    public Long getPostulationId() {
        return postulationId;
    }

    public void setPostulationId(Long postulationId) {
        this.postulationId = postulationId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
