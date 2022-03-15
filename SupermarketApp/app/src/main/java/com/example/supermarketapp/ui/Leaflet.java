package com.example.supermarketapp.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;

public class Leaflet {

    private String title;
    private String description;

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    private Drawable image;

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
