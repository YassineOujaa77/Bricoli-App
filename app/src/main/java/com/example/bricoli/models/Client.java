package com.example.bricoli.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Client extends User implements Serializable {
    private Set<Offer> offers = new HashSet<>();



    public Client(Long userId, String cin, String password, String address, Long sommeRating, Integer numberOfRating, String photo, String fullName, String workerField, String phone,String token) {
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
        setToken(token);
    }


    public Client( String cin, String password, String address, Long sommeRating, Integer numberOfRating, String photo, String fullName, String workerField, String phone, String token) {
        setCin(cin);
        setPassword(password);
        setAddress(address);
        setSommeRating(sommeRating);
        setNumberOfRating(numberOfRating);
        setPhoto(photo);
        setFullName(fullName);
        setWorkerField(workerField);
        setPhone(phone);
        setToken(token);
    }



    public Client() {
    }


    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "Client{" + super.toString() +
                "offers=" + offers +
                '}';
    }
}
