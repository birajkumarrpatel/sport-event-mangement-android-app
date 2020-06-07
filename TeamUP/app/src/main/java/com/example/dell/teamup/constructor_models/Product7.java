package com.example.dell.teamup.constructor_models;

/**
 * Created by Dell on 4/11/2018.
 */

public class Product7 {
    private String teamname,captain;

    public Product7(String teamname,String captain) {
        this.teamname = teamname;
        this.captain = captain;

    }

    public String getTeamname()
    {
        return teamname;
    }
    public String getCaptain()
    {
        return captain;
    }
}
