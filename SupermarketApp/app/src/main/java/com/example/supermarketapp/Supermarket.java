package com.example.supermarketapp;

public class Supermarket {

    private String name;
    private String lat;
    private String lng;
    private String openingTime;

    public Supermarket(String name, String lat, String lng, String openingTime) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.openingTime = openingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    @Override
    public String toString() {
        return "Supermarket{" +
                "name='" + name + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", openingTime='" + openingTime + '\'' +
                '}';
    }
}
