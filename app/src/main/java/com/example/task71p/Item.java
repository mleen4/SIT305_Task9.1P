package com.example.task71p;

public class Item {


    private String name;
    private int phone;
    private String description;
    private String date;

    private double locationLat;
    private double locationLong;
    private boolean lost;

    public Item(String name, int phone, String description, String date, double locationlat, double locationlong, boolean lost) {
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.locationLat = locationlat;
        this.locationLong = locationlong;
        this.lost = lost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(double locationLat) {
        this.locationLat = locationLat;
    }

    public double getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(double locationLong) {
        this.locationLong = locationLong;
    }
}
