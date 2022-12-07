package com.example.bricoli.models;

public class Post
{
    private String Categorie;
    private String Period;
    private String Description;
    private String NbBids;
    private String Etat;

    public Post(String categorie, String period, String description, String nbBids, String etat) {
        Categorie = categorie;
        Period = period;
        Description = description;
        NbBids = nbBids;
        Etat = etat;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getNbBids() {
        return NbBids;
    }

    public void setNbBids(String nbBids) {
        NbBids = nbBids;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String etat) {
        Etat = etat;
    }

}
