package com.example.bricoli.models;

public class Bid
{
    int Photo;
    String Ville;
    String NomComplet;
    String Note;
    String Prix;
    String Period;
    String Distance;
    String Etat;

    public Bid(int photo, String ville, String nomComplet, String note, String prix, String period, String distance,String etat) {
        Photo = photo;
        Ville = ville;
        NomComplet = nomComplet;
        Note = note;
        Prix = prix;
        Period = period;
        Distance = distance;
        Etat=etat;
    }

    public int getPhoto() {
        return Photo;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getNomComplet() {
        return NomComplet;
    }

    public void setNomComplet(String nomComplet) {
        NomComplet = nomComplet;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getPrix() {
        return Prix;
    }

    public void setPrix(String prix) {
        Prix = prix;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String etat) {
        Etat = etat;
    }

}
