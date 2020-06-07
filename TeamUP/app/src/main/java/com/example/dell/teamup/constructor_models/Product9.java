package com.example.dell.teamup.constructor_models;

/**
 * Created by Dell on 5/9/2018.
 */

public class Product9 {
    String name, DOB, city,status;



    public Product9(String name, String DOB, String city, String status) {
        this.name = name;
        this.DOB = DOB;
        this.city = city;
        this.status = status;


    }

    public String getName() {
        return name;
    }

    public String getDOB() {
        return DOB;
    }

    public String getCity() {
        return city;
    }
    public String getStatus() {
        return status;
    }
}
