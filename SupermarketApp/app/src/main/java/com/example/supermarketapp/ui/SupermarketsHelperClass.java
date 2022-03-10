package com.example.supermarketapp.ui;

public class SupermarketsHelperClass {
    String id;
    String name;
    String location;
    String opening_times;

    public SupermarketsHelperClass() {
    }

    public SupermarketsHelperClass(String id, String name, String location, String opening_times) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.opening_times = opening_times;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOpening_times() {
        return opening_times;
    }

    public void setOpening_times(String opening_times) {
        this.opening_times = opening_times;
    }
}
