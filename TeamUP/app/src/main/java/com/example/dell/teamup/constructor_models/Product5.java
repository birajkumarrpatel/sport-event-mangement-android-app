package com.example.dell.teamup.constructor_models;

/**
 * Created by Dell on 4/7/2018.
 */

public class Product5 {
    private String teamname,captain,image;

    public Product5(String teamname,String captain,String image) {
        this.teamname = teamname;
        this.captain = captain;
        this.image = image;

    }

    public String getTeamname()
    {
        return teamname;
    }
    public String getCaptain()
    {
        return captain;
    }
    public String getImage()
    {
        return image;
    }
}
