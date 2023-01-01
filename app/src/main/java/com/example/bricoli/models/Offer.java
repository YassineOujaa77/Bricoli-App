package com.example.bricoli.models;

import com.google.android.gms.common.api.Api;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Offer {

    private Long offerId;
    private String category;
    private Client client;
    private String description;
    private String state;
    private Date createdAt;
    private Set<Postulation> postulations = new HashSet<>();

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<Postulation> getPostulations() {
        return postulations;
    }

    public void setPostulations(Set<Postulation> postulations) {
        this.postulations = postulations;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerId=" + offerId +
                ", category='" + category + '\'' +
                ", client=" + client +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", createdAt=" + createdAt +
                ", postulations=" + postulations +
                '}';
    }
}
