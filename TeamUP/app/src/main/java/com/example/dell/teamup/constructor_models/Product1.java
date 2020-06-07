package com.example.dell.teamup.constructor_models;

/**
 * Created by Dell on 4/2/2018.
 */

public class Product1 {
    private String eventname;
    private String image;
    private String location;

    public Product1(String eventname,String image,String location) {
        this.eventname = eventname;
        this.image = image;
        this.location = location;
    }

    public String getEventname() {
        return eventname;
    }
    public String getImage()
    {
        return image;
    }
    public String getLocation()
    {
        return location;
    }
}
