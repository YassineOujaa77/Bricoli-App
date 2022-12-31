package com.example.bricoli.models;

import java.util.HashSet;
import java.util.Set;

public class Client extends User{
    private Set<Offer> offers = new HashSet<>();
    public Client(Long userId, String cin, String password, String address, Long sommeRating, Integer numberOfRating, String photo, String fullName, String workerField, String phone) {
        setUserId(userId);
        setCin(cin);
        setPassword(password);
        setAddress(address);
        setSommeRating(sommeRating);
        setNumberOfRating(numberOfRating);
        setPhoto(photo);
        setFullName(fullName);
        setWorkerField(workerField);
        setPhone(phone);
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
}
