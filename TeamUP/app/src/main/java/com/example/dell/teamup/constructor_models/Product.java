package com.example.dell.teamup.constructor_models;

/**
 * Created by Dell on 4/1/2018.
 */

public class Product {

    private String eventname;
    private String sport;
    private String image;
    private String location;
    private String date;

    public Product(String eventname, String sport,String image,String location,String date) {
        this.eventname = eventname;
        this.sport = sport;
        this.image = image;
        this.location = location;
        this.date = date;

    }

    public String getTitle() {
        return eventname;
    }

    public String getShortdesc() {
        return sport;
    }
    public String getImage()
    {
        return image;
    }
    public String getLocation()
    {
        return location;
    }
    public String getDate() {
        return date;
    }
}
