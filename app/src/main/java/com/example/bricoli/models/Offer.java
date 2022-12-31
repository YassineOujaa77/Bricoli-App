package com.example.bricoli.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Offer
{
    @SerializedName("offerId")
    private Long offerId;

    @SerializedName("category")
    private String category;

    @SerializedName("client")
    private Client client;

    @SerializedName("description")
    private String description;

    @SerializedName("state")
    private String state;

    @SerializedName("createdAt")
    private Date createdAt;

    @SerializedName("postulations")
    private Set<Postulation> postulations;

    public Long getOfferId() {
        return offerId;
    }

    public String getCategory() {
        return category;
    }

    public Client getClient() {
        return client;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Set<Postulation> getPostulations() {
        return postulations;
    }
}
