package com.example.bricoli.models;

public class History
{
    int Image;
    String NomComplet;
    String Ville;
    String Note;
    String Date;
    String Description;

    public History(int image, String nomComplet, String ville, String note, String date, String description) {
        Image = image;
        NomComplet = nomComplet;
        Ville = ville;
        Note = note;
        Date = date;
        Description = description;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getNomComplet() {
        return NomComplet;
    }

    public void setNomComplet(String nomComplet) {
        NomComplet = nomComplet;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
