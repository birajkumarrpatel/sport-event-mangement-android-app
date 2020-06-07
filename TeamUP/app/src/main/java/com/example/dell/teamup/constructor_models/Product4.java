package com.example.dell.teamup.constructor_models;

/**
 * Created by Dell on 4/7/2018.
 */

public class Product4 {
    private String teamname,captain,eventname,status;

    public Product4(String teamname,String captain,String eventname,String status) {
        this.teamname = teamname;
        this.captain = captain;
        this.eventname = eventname;
        this.status = status;

    }

    public String getTeamname()
    {
        return teamname;
    }
    public String getCaptain()
    {
        return captain;
    }
    public String getEventname()
    {
        return eventname;
    }

    public String getStatus() {
        return status;
    }
}
