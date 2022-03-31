package com.example.supermarketapp.ui.leaflets;

import android.graphics.drawable.Drawable;

// Class to create leaflet object
public class Leaflet {

    private String title;
    private String description;
    private Drawable image;

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
