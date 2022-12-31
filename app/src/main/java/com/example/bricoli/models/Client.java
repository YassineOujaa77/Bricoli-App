package com.example.bricoli.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

public class Client extends User
{
    @SerializedName("offers")
    private Set<Offer> offers;

    public Set<Offer> getOffers() {
        return offers;
    }

}
